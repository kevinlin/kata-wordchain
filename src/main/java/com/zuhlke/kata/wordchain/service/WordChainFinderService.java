package com.zuhlke.kata.wordchain.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.zuhlke.kata.wordchain.dictionary.Dictionary;
import com.zuhlke.kata.wordchain.dictionary.WordChainFinder;

@Service
public class WordChainFinderService {

    private List<String> lines;

    public void loadDictionaryFile(String dictionaryFile) {
        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream(dictionaryFile); BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            lines = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumOfLines() {
        return lines.size();
    }

    public List<String> getWordChain(String start, String end) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Dictionary dictionary = new Dictionary(lines.stream().filter(w -> w.length() == start.length()).toArray(String[]::new));
        WordChainFinder wordChainFinder = new WordChainFinder(dictionary);
        List<String> wordChain = wordChainFinder.getWordChain(start, end);
        stopWatch.stop();
        System.out.print("It took %d ms to find the first word chain.");
        return wordChain;
    }

}
