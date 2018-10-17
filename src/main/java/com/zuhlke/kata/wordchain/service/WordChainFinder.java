package com.zuhlke.kata.wordchain.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordChainFinder {

    private Dictionary dictionary;

    public WordChainFinder(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getWordChain(String start, String end) {
        if (!dictionary.isInDictionary(start) || !dictionary.isInDictionary(end)) {
            throw new IllegalArgumentException("Word not in dictionary");
        }
        return getWordChain(start, end, new HashSet<>());
    }

    private List<String> getWordChain(String start, String end, Set<String> visited) {
        if (visited.contains(start)) {
            return null;
        }
        visited.add(start);

        ArrayList<String> path = new ArrayList<>();
        path.add(start);

        if (start.equals(end)) {
            return path;
        }

        List<String> adjacentWords = dictionary.getAdjacentWords(start);
        for (String word : adjacentWords) {
            List<String> pathToEnd = getWordChain(word, end, visited);
            if (pathToEnd != null) {
                path.addAll(pathToEnd);
                return path;
            }

        }
        return null;
    }

}
