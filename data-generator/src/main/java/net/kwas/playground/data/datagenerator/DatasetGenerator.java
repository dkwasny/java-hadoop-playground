package net.kwas.sparkplayground.generate;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import net.kwas.sparkplayground.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

public class DatasetGenerator {

    private static class Arguments {
        @Parameter(names = { "-d", "--dictionary-file"}, description = "Dictionary file typically found in /usr/share/dict", required = true)
        private String dictionaryFile;

        @Parameter(names = {"-o", "--output-file"}, description = "Output file", required = true)
        private String outputFile;

        @Parameter(names = {"-m", "--max-words"}, description = "Maximum number of words per entry")
        private int maxWords = 5;

        @Parameter(names = {"-n", "--num-entries"}, description = "Number of entries to generate")
        private int numEntries = 10_000;

        @Parameter(names = {"-s", "--seed"}, description = "Random seed")
        private int seed = 13;
    }

    public static void main(String[] args) throws Exception {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        Path dictPath = Paths.get(arguments.dictionaryFile);
        Path outPath = Paths.get(arguments.outputFile);

        List<String> dictWords = Files.readAllLines(dictPath);
        Random rand = new Random(arguments.seed);
        try (
            BufferedWriter writer = Files.newBufferedWriter(outPath);
            CSVPrinter printer = new CSVPrinter(writer, Constants.CSV_FORMAT)
        ) {
            for (int i = 0; i < arguments.numEntries; i++) {
                GeneratedPhrase phrase = generatePhrase(rand, dictWords, arguments.maxWords);
                printer.printRecord(
                    i,
                    phrase.getPhrase(),
                    phrase.getNumWords(),
                    phrase.getNumChars(),
                    phrase.getTotalWordScore(),
                    phrase.getAvgWordScore(),
                    phrase.getMinWordScore(),
                    phrase.getMaxWordScore(),
                    phrase.getAvgCharScore(),
                    phrase.getMinCharScore(),
                    phrase.getMaxCharScore(),
                    phrase.getPhraseMd5()
                );
            }
            printer.flush();
        }
    }

    private static GeneratedPhrase generatePhrase(Random random, List<String> dictWords, int maxWords) {
        int numWords = random.nextInt(maxWords) + 1;
        StringBuilder phraseBuilder = new StringBuilder();
        int totalWordScore = 0;
        int minWordScore = Integer.MAX_VALUE;
        int maxWordScore = 0;
        int minCharScore = Integer.MAX_VALUE;
        int maxCharScore = 0;
        int numChars = 0;

        for (int i = 0; i < numWords; i++) {
            String word = dictWords.get(random.nextInt(dictWords.size()));
            if (i != 0) {
                phraseBuilder.append(' ');
            }
            phraseBuilder.append(word);
            int wordScore = word.chars().sum();
            int minChar = word.chars().min().getAsInt();
            int maxChar = word.chars().max().getAsInt();

            totalWordScore += wordScore;
            minWordScore = Math.min(wordScore, minWordScore);
            maxWordScore = Math.max(wordScore, maxWordScore);
            minCharScore = Math.min(minChar, minCharScore);
            maxCharScore = Math.max(maxChar, maxCharScore);
            numChars += word.length();
        }

        String phrase = phraseBuilder.toString();
        String phraseMd5 = DigestUtils.md5Hex(phrase);
        int avgWordScore = totalWordScore / numWords;
        int avgCharScore = totalWordScore / numChars;
        return new GeneratedPhrase(
            phrase,
            numWords,
            numChars,
            totalWordScore,
            avgWordScore,
            minWordScore,
            maxWordScore,
            avgCharScore,
            minCharScore,
            maxCharScore,
            phraseMd5
        );
    }

}
