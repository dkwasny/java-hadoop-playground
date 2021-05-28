package net.kwas.sparkplayground.process;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import net.kwas.sparkplayground.Constants;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.StringReader;
import java.util.List;

public class RDDPlayground {

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

        log("----- Native RDD");
        JavaRDD<String> rdd = sc.textFile(inputFile);
        printTotalScoreSum(rdd);
        printTotalScoreSumWithCommonsCsv(rdd);

        log("----- DataFrame to RDD");
        SparkSession session = SparkSession.builder()
            .config(conf)
            .getOrCreate();
        JavaRDD<Row> rowRdd = session.read()
            .option("header", true)
            .csv(inputFile)
            .toJavaRDD();
        printTotalScoreSumWithDataFrameRDD(rowRdd);

        session.close();
        sc.close();
    }

    private static void printTotalScoreSumWithDataFrameRDD(JavaRDD<Row> rdd) {
        int summedWordScore = rdd.map(x -> {
            int totalWordScore = Integer.parseInt(x.getAs("totalWordScore"));
            return totalWordScore;
        }).reduce((x, y) -> x + y);

        System.out.println("Total Word Score Sum With DataFrame RDD: " + summedWordScore);
    }

    private static void printTotalScoreSum(JavaRDD<String> rdd) {
        final String columnName = "totalWordScore";

        int summedWordScore = rdd
            .map(x -> {
                String value = x.split(",")[4];
                return value.equals(columnName) ? 0 : Integer.parseInt(value);
            }).reduce((x, y) -> x + y);

        System.out.println("Total Word Score Sum: " + summedWordScore);
    }

    private static void printTotalScoreSumWithCommonsCsv(JavaRDD<String> rdd) {
        final String columnName = "totalWordScore";

        int summedWordScore = rdd
            .map(x -> {
                // Commons CSV doesn't really mesh well with Spark.
                StringReader reader = new StringReader(x);
                CSVParser parser = Constants.CSV_FORMAT.parse(reader);
                List<CSVRecord> records = parser.getRecords();
                if (records.isEmpty()) {
                    return 0;
                }
                String val = records.get(0).get(columnName);
                int totalWordScore = val.equals(columnName) ? 0 : Integer.parseInt(val);
                return totalWordScore;
            }).reduce((x, y) -> x + y);

        System.out.println("Commons CSV Total Word Score Sum: " + summedWordScore);
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
