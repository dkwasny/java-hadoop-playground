package net.kwas.playground.data.spark;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;
import scala.collection.JavaConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PercentageTest {

    private static class Arguments {
        @Parameter(names = { "-i", "--input-file"}, description = "Input file", required = true)
        private String inputFile;

        @Parameter(names = { "-o", "--output-file"}, description = "Output file", required = true)
        private String outputFile;

        @Parameter(names = {"-m", "--master"}, description = "Spark master")
        private String master = "local[2,4]";
    }

    private static class PercentageListener extends SparkListener {
        private final List<String> stagePrefixes;
        private final Set<Integer> validStageIds = new HashSet<>();

        private long lastPercent = -1;
        private int totalTasks;
        private int tasksCompleted;

        public PercentageListener(List<String> stagePrefixes) {
            this.stagePrefixes = stagePrefixes;
        }

        @Override
        public void onTaskEnd(SparkListenerTaskEnd taskEnd) {
            if (taskEnd.taskInfo().failed()) {
                System.out.println("SKIPPING EVENT FAILURE!");
            }
            else if (validStageIds.contains(taskEnd.stageId())){
                tasksCompleted += 1;
                long currentPercent = Math.round((double) tasksCompleted / totalTasks * 100);
                if (currentPercent > lastPercent) {
                    lastPercent = currentPercent;
                    System.out.println(currentPercent + "% Complete");
                }
            }
        }

        @Override
        public void onJobStart(SparkListenerJobStart event) {
            lastPercent = -1;
            validStageIds.clear();
            totalTasks = 0;
            List<StageInfo> stageInfos = JavaConverters.seqAsJavaList(event.stageInfos());
            for (StageInfo stageInfo : stageInfos) {
                boolean stageMatch = stagePrefixes.stream()
                        .anyMatch(stageInfo.name()::startsWith);
                if (stageMatch) {
                    validStageIds.add(stageInfo.stageId());
                    totalTasks += stageInfo.numTasks();
                }
            }
        }
    }

    private static final String PHRASE_COLUMN = "phrase";
    private static final String METRIC_COLUMN = "metric";
    private static final String FIRST_WORD_COLUMN = "first_word";
    private static final String SUM_COLUMN = "sum";

    public static void main(String[] args) throws InterruptedException {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        SparkConf conf = new SparkConf()
                .setAppName("PercentageTest")
                .setMaster(arguments.master);

        try (SparkSession session = SparkSession.builder()
                .config(conf)
                .getOrCreate()) {
            SparkContext ctx = session.sparkContext();

            SparkListener percentListener = new PercentageListener(Arrays.asList(
                    "mapToPair at PercentageTest.java:",
                    "csv at PercentageTest.java:"
            ));
            ctx.addSparkListener(percentListener);

            StructType metricSchema = new StructType()
                    .add(PHRASE_COLUMN, DataTypes.StringType, false)
                    .add(METRIC_COLUMN, DataTypes.IntegerType, false);

            StructType resultSchema = new StructType()
                    .add(FIRST_WORD_COLUMN, DataTypes.StringType, false)
                    .add(SUM_COLUMN, DataTypes.IntegerType, false);

            JavaRDD<Row> rdd1 = session
                    .read()
                    .parquet(arguments.inputFile)
                    .toJavaRDD()
                    .repartition(333)
                    .map(x -> evaluate(x, metricSchema));

            JavaRDD<Row> rdd2 = session
                    .read()
                    .parquet(arguments.inputFile)
                    .toJavaRDD()
                    .repartition(100)
                    .map(x -> evaluate(x, metricSchema));

            JavaRDD<Row> results = rdd1
                    .union(rdd2)
                    .mapToPair(x -> {
                        String key = x.getString(0).split(" ")[0];
                        return new Tuple2<>(key, x);
                    })
                    .combineByKey(
                            (x) -> {
                                List<Row> retVal = new ArrayList<>();
                                retVal.add(x);
                                return retVal;
                            },
                            (x, y) -> {
                                x.add(y);
                                return x;
                            },
                            (x, y) -> {
                                List<Row> retVal = new ArrayList<>();
                                retVal.addAll(x);
                                retVal.addAll(y);
                                return retVal;
                            }
                    )
                    .flatMapToPair(x -> {
                        int count = x._2.stream()
                                .map(y -> (int)y.getAs(METRIC_COLUMN))
                                .reduce(Integer::sum)
                                .get();
                        List<Tuple2<String, Integer>> retVal = new ArrayList<>();
                        retVal.add(new Tuple2<>(x._1, count));
                        return retVal.iterator();
                    })
                    .map(x -> {
                        Object[] values = new Object[2];
                        values[0] = x._1;
                        values[1] = x._2;
                        return new GenericRowWithSchema(values, resultSchema);
                    });

            session.createDataFrame(results, resultSchema)
                    .write()
                    .mode(SaveMode.Overwrite)
                    .csv(arguments.outputFile);

            System.out.println("FINISHED AND SLEEPING");
            Thread.sleep(1000000000L);
        }

    }

    private static Row evaluate(Row row, StructType outputSchema) {
        int avgWordScore = row.getAs("avgWordScore");
        int avgCharScore = row.getAs("avgCharScore");

        // Sleep to fake load
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (ThreadLocalRandom.current().nextDouble() < 0.001) {
            throw new RuntimeException("RANDOM FAILURE");
        }

        Object[] retVal = new Object[2];
        retVal[0] = row.getAs(PHRASE_COLUMN);
        retVal[1] = avgWordScore / avgCharScore;
        return new GenericRowWithSchema(retVal, outputSchema);
    }

}
