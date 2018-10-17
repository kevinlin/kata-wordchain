package com.zuhlke.kata.wordchain;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {

    private static final String[] words = new String[]{"Hello", "World", "cat", "cot", "cog", "dog"};
    private Dictionary dictionary;

    @Before
    public void setup() {
        dictionary = new Dictionary(words);
    }

    @Test
    public void isInDictionary_returnsTrue_forHello() {
        assertTrue(dictionary.isInDictionary("Hello"));
    }

    @Test
    public void isInDictionary_returnsFalse_forInexistentWord() {
        assertFalse(dictionary.isInDictionary("asdjfkl"));
    }

    @Test
    public void isInDictionary_returnsTrue_onCaseInsensitiveMatch() {
        assertTrue(dictionary.isInDictionary("hello"));
    }

    @Test
    public void getAdjacentWords_returnsWordsOneStepAway() {
        List<String> adjacentWords = dictionary.getAdjacentWords("cot");
        List<String> expectedWords = Arrays.asList("cat", "cog");
        assertThat(adjacentWords, is(expectedWords));
    }

    @Test
    public void getDistance_returns1_forCatCot() {
        assertEquals(1, Dictionary.getDistance("cat", "cot"));
    }

    @Test
    public void getDistance_returns1_forHelloWorld() {
        assertEquals(4, Dictionary.getDistance("Hello", "World"));
    }

    @Test
    public void getDistance_returnsNegative1_forWordsOfDifferentLength() {
        assertEquals(-1, Dictionary.getDistance("Hello", "You"));
    }

    @Test
    public void getDistance_returnsNegative1_ifFirstWordIsNull() {
        assertEquals(-1, Dictionary.getDistance(null, "You"));
    }

    @Test
    public void getDistance_returnsNegative1_ifSecodnWordIsNull() {
        assertEquals(-1, Dictionary.getDistance("Hello", null));
    }

}