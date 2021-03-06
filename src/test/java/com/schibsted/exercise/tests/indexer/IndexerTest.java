package com.schibsted.exercise.tests.indexer;

import junit.framework.Assert;
import org.junit.*;
import java.io.*;

import com.schibsted.exercise.indexer.Indexer;

public class IndexerTest {

    @Test
    public void IndexerPathNullLaunchException() throws IOException {
        boolean thrown = false;

        try {
            new Indexer(new File(""));
        } catch (NullPointerException e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

    @Test
    public void IndexerPathNotExistsLaunchException() throws IOException {
        boolean thrown = false;
        File resourcesDirectory = new File("src/test/resources/NotExists");

        try {
            new Indexer(resourcesDirectory);
        } catch (NullPointerException e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

    @Test
    public void indexerFileData1() throws IOException {
        File resourcesDirectory = new File("src/test/resources/data1.txt");
        new Indexer(resourcesDirectory);

        Assert.assertTrue(Indexer.index.size() == 83);
        Assert.assertTrue(Indexer.files.get(0).words() == 175);
        Assert.assertEquals(Indexer.files.get(0).file(), "data1.txt");
    }

    @Test
    public void indexerFileStopWords() throws IOException {
        File resourcesDirectory = new File("src/test/resources/stopwords.txt");
        new Indexer(resourcesDirectory);

        Assert.assertTrue(Indexer.index.size() == 0);
        Assert.assertTrue(Indexer.files.get(0).words() == 4);
        Assert.assertEquals(Indexer.files.get(0).file(), "stopwords.txt");
    }

    @Test
    public void indexerDirectory() throws IOException {
        File resourcesDirectory = new File("src/test/resources");
        new Indexer(resourcesDirectory);

        Assert.assertTrue(Indexer.files.size() == 4);
    }

    @After
    public void after(){
        Indexer.files.clear();
        Indexer.index.clear();
    }
}
