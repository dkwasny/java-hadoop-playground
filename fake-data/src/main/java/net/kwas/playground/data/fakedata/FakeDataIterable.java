package net.kwas.playground.data.fakedata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FakeDataIterable implements Iterable<FakeData> {

    public static FakeDataIterable create(
            int numEntries,
            int maxWordsPerEntry,
            Random random,
            String dictFilename
    ) throws IOException {
        Path dictFilePath = Paths.get(dictFilename);
        List<String> dictWords = Files.readAllLines(dictFilePath);
        return new FakeDataIterable(numEntries, maxWordsPerEntry, random, dictWords);
    }

    private final int numEntries;
    private final int maxWordsPerEntry;
    private final Random random;
    private final List<String> dictWords;

    public FakeDataIterable(int numEntries, int maxWordsPerEntry, Random random, List<String> dictWords) {
        this.numEntries = numEntries;
        this.maxWordsPerEntry = maxWordsPerEntry;
        this.random = random;
        this.dictWords = dictWords;
    }

    @Override
    public Iterator<FakeData> iterator() {
        return new FakeDataIterator(numEntries, maxWordsPerEntry, random, dictWords);
    }

}
