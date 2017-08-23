package com.schibsted.exercise.tests.searcher;

import junit.framework.Assert;
import org.junit.*;
import java.io.*;
import java.util.*;

import com.schibsted.exercise.indexer.Indexer;
import com.schibsted.exercise.searcher.Searcher;

public class SearcherTest {
    @Test
    public void searcherFindBasicTest() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("Hello Business");

        Assert.assertTrue(Searcher.searchResults.get(0).words.size() == 2);
        Assert.assertTrue(Searcher.searchResults.size() == 1);
        Assert.assertEquals(Searcher.searchResults.get(0).file(), "data1.txt");
    }

    @Test
    public void searcherFindWords() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("nato italian");

        Assert.assertTrue(Searcher.searchResults.get(0).words.size() == 2);
        Assert.assertEquals(Searcher.searchResults.get(0).words.toString(), Arrays.asList(new Boolean[]{true, false}).toString());
    }

    @Test
    public void searcherFindWordsSpecialChars() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("nat@!o italian");

        Assert.assertTrue(Searcher.searchResults.get(0).words.size() == 2);
        Assert.assertEquals( Searcher.searchResults.get(0).words.toString(), Arrays.asList(new Boolean[] {true,false}).toString());
    }

    @Test
    public void RankTestHalfWords() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("nato italian");

        Assert.assertTrue(Searcher.calculateRank(Searcher.searchResults.get(0)) == 50.0);
    }

    @Test
    public void RankTestNoWords() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("france italian");

        Assert.assertTrue(Searcher.calculateRank(Searcher.searchResults.get(0)) == 0.0);
    }

    @Test
    public void RankTestFullWords() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("nato president");

        Assert.assertTrue(Searcher.calculateRank(Searcher.searchResults.get(0)) == 100.0);
    }

    @Test
    public void RankTestFullEmptyWords() throws Exception {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);
        new Searcher("this is you");

        Assert.assertTrue(Searcher.calculateRank(Searcher.searchResults.get(0)) == 0.0);
    }
}
