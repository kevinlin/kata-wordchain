package com.zuhlke.kata.wordchain;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class WordChainFinderTest {

    private WordChainFinder wordChainFinder;

    @Before
    public void setUp() {
        Dictionary dictionary = new Dictionary("cat", "cot", "cog", "dog", "abc");
        wordChainFinder = new WordChainFinder(dictionary);
    }

    @Test
    public void getWordChain_withLongChain_works() {
        assertThat(wordChainFinder.getWordChain("cat", "dog"), is(Arrays.asList("cat", "cot", "cog", "dog")));
    }

    @Test
    public void getWordChain_withOneStepDistance_works() {
        assertThat(wordChainFinder.getWordChain("cat", "cot"), is(Arrays.asList("cat", "cot")));
    }

    @Test
    public void getWordChain_withTwoStepDistance_works() {
        assertThat(wordChainFinder.getWordChain("cat", "cog"), is(Arrays.asList("cat", "cot", "cog")));
    }

    @Test
    public void getWordChain_withNoSolution_returnsNull() {
        assertNull(wordChainFinder.getWordChain("cat", "abc"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWordChain_withUnknownStartWord_throws() {
        wordChainFinder.getWordChain("asdfjl", "cat");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWordChain_withUnknownEndWord_throws() {
        wordChainFinder.getWordChain("cat", "ajsdfkl");
    }
}