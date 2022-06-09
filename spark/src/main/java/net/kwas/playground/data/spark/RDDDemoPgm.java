package net.kwas.playground.data.spark;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class RDDDemoPgm {


    private static class Arguments {
        @Parameter(names = { "-i", "--input-file"}, description = "Input file", required = true)
        private String inputFile;

        @Parameter(names = {"-o", "--output-file"}, description = "Output file", required = true)
        private String outputFile;

        @Parameter(names = {"-m", "--master"}, description = "Spark master")
        private String master = "local[4]";
    }

    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        SparkConf conf = new SparkConf()
                .setAppName("RDDPlayground")
                .setMaster(arguments.master);

        JavaSparkContext sc = new JavaSparkContext(conf);

        String inputFile = arguments.inputFile;

        log("----- DataFrame to RDD");
        SparkSession session = SparkSession.builder()
                .config(conf)
                .getOrCreate();
        JavaRDD<Row> rowRdd = session.read()
                .parquet(inputFile)
                .toJavaRDD();
        printTotalScoreSumWithDataFrameRDD(rowRdd);

        session.read()
                .parquet(inputFile)
                .printSchema();

        session.close();
        sc.close();
    }

    private static void printTotalScoreSumWithDataFrameRDD(JavaRDD<Row> rdd) {
        int summedWordScore = rdd.map(x -> {
            int totalWordScore = x.getAs("totalWordScore");
            return totalWordScore;
        }).reduce(Integer::sum);

        System.out.println("Total Word Score Sum With DataFrame RDD: " + summedWordScore);
    }

    private static void log(String message) {
        System.out.println(message);
    }

}
