package net.kwas.playground.data.fakedata;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FakeDataIterator implements Iterator<FakeData> {

    private final int numEntries;
    private final int maxWordsPerEntry;
    private final Random random;
    private final List<String> dictWords;
    private int currEntry = 0;

    public FakeDataIterator(int numEntries, int maxWordsPerEntry, Random random, List<String> dictWords) {
        this.numEntries = numEntries;
        this.maxWordsPerEntry = maxWordsPerEntry;
        this.random = random;
        this.dictWords = dictWords;
    }

    @Override
    public boolean hasNext() {
        return currEntry < numEntries;
    }

    @Override
    public FakeData next() {
        FakeData retVal = null;
        if (hasNext()) {
            retVal = generatePhrase(random, dictWords, maxWordsPerEntry);
            currEntry += 1;
        }
        return retVal;
    }

    private FakeData generatePhrase(Random random, List<String> dictWords, int maxWords) {
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
        return new FakeData(
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
