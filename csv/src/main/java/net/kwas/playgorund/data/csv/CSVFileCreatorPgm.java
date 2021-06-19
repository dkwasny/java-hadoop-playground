package net.kwas.playgorund.data.csv;

import net.kwas.playground.data.fakedata.FakeData;
import net.kwas.playground.data.fakedata.FakeDataIterable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CSVFileCreatorPgm {

    private static final String OUTPUT_FILE = "/tmp/test-file.csv";
    
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.withHeader(
            "phrase",
            "numWords",
            "numChars",
            "totalWordScore",
            "avgWordScore",
            "minWordScore",
            "maxWordScore",
            "avgCharScore",
            "minCharScore",
            "maxCharScore",
            "classification",
            "phraseMd5"
    );
    
    public static void main(String[] args) throws IOException {
        Iterable<FakeData> iterable = FakeDataIterable.create(
                1000,
                5,
                new Random(13),
                "/usr/share/dict/words"
        );
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE));
                CSVPrinter printer = new CSVPrinter(writer, CSV_FORMAT)
        ) {
            for (FakeData fakeData : iterable) {
                printer.printRecord(
                        fakeData.getPhrase(),
                        fakeData.getNumWords(),
                        fakeData.getNumChars(),
                        fakeData.getTotalWordScore(),
                        fakeData.getAvgWordScore(),
                        fakeData.getMinWordScore(),
                        fakeData.getMaxWordScore(),
                        fakeData.getAvgCharScore(),
                        fakeData.getMinCharScore(),
                        fakeData.getMaxCharScore(),
                        fakeData.getClassification(),
                        fakeData.getPhraseMd5()
                );
            }
            printer.flush();
        }
    }

}
