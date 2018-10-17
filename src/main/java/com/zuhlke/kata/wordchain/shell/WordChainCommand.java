package com.zuhlke.kata.wordchain.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.zuhlke.kata.wordchain.service.WordChainFinderService;

@ShellComponent
public class WordChainCommand {

    @Autowired
    private WordChainFinderService wordChainFinderService;

    @ShellMethod(value = "Create a new word chain finder with dictionary file", key = {"load", "L"})
    public String create(String filename) {
        this.wordChainFinderService.loadDictionaryFile(filename);
        return String.format("New dictionary loaded with %d words", wordChainFinderService.getNumOfLines());
    }

    @ShellMethod(value = "Find word chain", key = {"chain", "WC"})
    public String wordChain(String start, String end) {
        List<String> path = this.wordChainFinderService.getWordChain(start, end);
        if (path == null) {
            return "No path found";
        }
        StringBuilder sb = new StringBuilder();
        for (String step : path) {
            sb.append(step);
            sb.append("\n");
        }
        sb.append(String.format("%d of steps in total", path.size()));

        return sb.toString();
    }

}
