package com.zuhlke.kata.wordchain.dictionary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {

    private final String[] words;

    public Dictionary(String... rawWords) {
        this.words = Arrays.stream(rawWords).map(String::toLowerCase).sorted().toArray(String[]::new);
        Arrays.sort(rawWords);
    }

    public boolean isInDictionary(String word) {
        return Arrays.binarySearch(words, word.toLowerCase()) >= 0;
    }

    public List<String> getAdjacentWords(String originWord) {
        String lowerCaseWord = originWord.toLowerCase();
        List<String> adjacentWords = new ArrayList<>();
        for (String word : words) {
            if (getDistance(lowerCaseWord, word) == 1) {
                adjacentWords.add(word);
            }
        }
        return adjacentWords;
    }

    static int getDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }

        if (word1.length() != word2.length()) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                count++;
        }
        return count;
    }

}
