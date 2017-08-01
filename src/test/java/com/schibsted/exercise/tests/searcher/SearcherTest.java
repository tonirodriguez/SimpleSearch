package com.schibsted.exercise.tests.searcher;

import junit.framework.Assert;
import org.junit.*;
import java.io.*;
import java.util.Arrays;

import com.schibsted.exercise.indexer.Indexer;
import com.schibsted.exercise.searcher.Searcher;

public class SearcherTest {
    @Test
    public void searcherFindBasicTest() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher(Arrays.asList("Hello", "Business"));

        Assert.assertTrue(Searcher.searchResults.get(0).words.size() == 2);
        Assert.assertTrue(Searcher.searchResults.size() == 1);
        Assert.assertEquals(Searcher.searchResults.get(0).file(), "data1.txt");
    }

}
