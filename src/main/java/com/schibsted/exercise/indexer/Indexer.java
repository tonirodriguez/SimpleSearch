package com.schibsted.exercise.indexer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;


public class Indexer {

    //TODO: Extract stopwords to a Text File
    List<String> stopwords = Arrays.asList("a", "able", "about",
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

    Map<String, List<Integer>> index = new HashMap<String, List<Integer>>();
    List<Tupla> files = new ArrayList<Tupla>();

    public Indexer(String path) throws IOException {

        final File dir = new File(path);
        final String[] directory = dir.list();

        for (String file: directory){
            files.add(new Tupla(file, indexFile(path, file)));
        }
        System.out.println(index);
        System.out.println(files);
    }

    public int indexFile(String path, String file) throws IOException {

        int countWords = 0;
        int posFile = files.size();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path, file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for (String _word : line.split("\\W+")) {
                    String word = _word.toLowerCase();
                    countWords++;
                    if (stopwords.contains(word))
                        continue;
                    List<Integer> idx = index.get(word);
                    if (idx == null) {
                        idx = new LinkedList<Integer>();
                        index.put(word, idx);
                    }
                    idx.add(posFile);
                }
            }
        }
        catch(IOException e) {
            System.out.println("Exception reading file " + path + "/" + file);
        }
        System.out.println("indexed " + file + " "  + countWords +" words");

        return(countWords);
    }

    private class Tupla {
        private String file;
        private int words;

        public Tupla(String file, int words) {
            this.file = file;
            this.words = words;
        }

        public String toString() {
            return (this.file + " " + this.words);
        }
    }
}
