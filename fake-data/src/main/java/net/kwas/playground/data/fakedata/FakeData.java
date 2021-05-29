package net.kwas.playground.data.fakedata;

public class FakeData {

    private final String phrase;
    private final int numWords;
    private final int numChars;
    private final int totalWordScore;
    private final int avgWordScore;
    private final int minWordScore;
    private final int maxWordScore;
    private final int avgCharScore;
    private final int minCharScore;
    private final int maxCharScore;
    private final String phraseMd5;

    public FakeData(
        String phrase,
        int numWords,
        int numChars,
        int totalWordScore,
        int avgWordScore,
        int minWordScore,
        int maxWordScore,
        int avgCharScore,
        int minCharScore,
        int maxCharScore,
        String phraseMd5
    ) {
        this.phrase = phrase;
        this.numWords = numWords;
        this.numChars = numChars;
        this.totalWordScore = totalWordScore;
        this.avgWordScore = avgWordScore;
        this.minWordScore = minWordScore;
        this.maxWordScore = maxWordScore;
        this.avgCharScore = avgCharScore;
        this.minCharScore = minCharScore;
        this.maxCharScore = maxCharScore;
        this.phraseMd5 = phraseMd5;
    }

    public String getPhrase() {
        return phrase;
    }

    public int getNumWords() {
        return numWords;
    }

    public int getNumChars() {
        return numChars;
    }

    public int getTotalWordScore() {
        return totalWordScore;
    }

    public int getAvgWordScore() {
        return avgWordScore;
    }

    public int getMinWordScore() {
        return minWordScore;
    }

    public int getMaxWordScore() {
        return maxWordScore;
    }

    public int getAvgCharScore() {
        return avgCharScore;
    }

    public int getMinCharScore() {
        return minCharScore;
    }

    public int getMaxCharScore() {
        return maxCharScore;
    }

    public String getPhraseMd5() {
        return phraseMd5;
    }
}
