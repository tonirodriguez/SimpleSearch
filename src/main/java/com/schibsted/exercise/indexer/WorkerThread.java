package com.schibsted.exercise.indexer;

import com.schibsted.exercise.Utils.Tupla;
import com.schibsted.exercise.Utils.Cleaner;
import com.schibsted.exercise.Utils.StopWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class WorkerThread implements Runnable {

    private String path;
    private String file;
    private int count;

    public WorkerThread(String p, String f, int v) {
        this.path = p;
        this.file = f;
        this.count = v;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Index File= " + file);
        try {
            indexFile(path, file, count);
        }
        catch (IOException e) {
            System.out.println("Exception on run method: " + e.getMessage());
        }
    }


    public synchronized int indexFile(String path, String file, int count) throws IOException {
        List<String> stopWords = new StopWords().stopwords;

        int countWords = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path, file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for (String word : new Cleaner(line).noSpecialChars) {//  .removeSplitAndRemoveSpecialChars()) {
                    countWords++;
                    if (stopWords.contains(word))
                        continue;
                    List<Integer> idx = Indexer.index.get(word);
                    if (idx == null) {
                        idx = new LinkedList<Integer>();
                        Indexer.index.put(word, idx);
                    }
                    idx.add(count);
                }
            }
            while (count >= Indexer.files.size()) {
                Indexer.files.add(new Tupla("Empty", 0));
            }
            Indexer.files.set(count, new Tupla(file, countWords));
        }
        catch(IOException e) {
            System.out.println("Exception reading file " + path + "/" + file);
        }
        System.out.println("indexed " + file + " "  + countWords + " words");

        return(countWords);
    }
}
