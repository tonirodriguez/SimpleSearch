package com.schibsted.exercise.searcher;

import java.util.*;

import com.schibsted.exercise.indexer.Indexer;

public class Searcher {

    public Searcher(List<String> words) throws Exception {
        for (String _word : words) {
            Set<String> answer = new HashSet<String>();
            String word = _word.toLowerCase();

            List<Integer> idx = Indexer.index.get(word);
            if (idx != null) {
                for (Integer i : idx) {
                    answer.add(Indexer.files.get(i).file());
                }
            }
            System.out.print(word);
            for (String f : answer) {
                System.out.print(" " + f);
            }
            System.out.println("");

        }
    }
}
