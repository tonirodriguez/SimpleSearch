package com.schibsted.exercise.indexer;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.schibsted.exercise.Utils.Tupla;

public class Indexer {



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

