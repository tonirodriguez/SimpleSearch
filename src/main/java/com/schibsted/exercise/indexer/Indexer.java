package com.schibsted.exercise.indexer;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.schibsted.exercise.Utils.Tupla;


public class Indexer {

    //TODO: Extract stopwords to a Text File
    public static List<String> stopwords = Arrays.asList("a", "able", "about",
            "across", "after", "all", "almost", "also", "am", "among", "an",
            "and", "any", "are", "as", "at", "be", "because", "been", "but",
            "by", "can", "cannot", "could", "dear", "did", "do", "does",
            "either", "else", "ever", "every", "for", "from", "get", "got",
            "had", "has", "have", "he", "her", "hers", "him", "his", "how",
            "however", "i", "if", "in", "into", "is", "it", "its", "just",
            "least", "let", "like", "likely", "may", "me", "might", "most",
            "must", "my", "neither", "no", "nor", "not", "of", "off", "often",
            "on", "only", "or", "other", "our", "own", "rather", "said", "say",
            "says", "she", "should", "since", "so", "some", "than", "that",
            "the", "their", "them", "then", "there", "these", "they", "this",
            "tis", "to", "too", "twas", "us", "wants", "was", "we", "were",
            "what", "when", "where", "which", "while", "who", "whom", "why",
            "will", "with", "would", "yet", "you", "your");

    public static Map<String, List<Integer>> index = new HashMap<String, List<Integer>>();
    public static List<Tupla> files = new ArrayList<Tupla>();
    private ExecutorService executor = Executors.newCachedThreadPool();
    private int count = 0;

    public Indexer(final File path) throws IOException {
        indexFilesForFolder(path);
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all Threads");
        //TODO: DELETE
        //System.out.println(index);
        //System.out.println(files);
    }


    public void indexFilesForFolder(final File folder) throws IOException {
        if (folder.isFile()) {
            Runnable worker = new WorkerThread(folder.getParent(), folder.getName(), count);
            count++;
            executor.execute(worker);
        } else {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    indexFilesForFolder(fileEntry);
                } else {
                    Runnable worker = new WorkerThread(folder.getPath(), fileEntry.getName(), count);
                    count++;
                    executor.execute(worker);
                }
            }
        }
    }
}

