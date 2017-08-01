package com.schibsted.exercise.searcher;

import java.util.*;

import com.schibsted.exercise.Utils.SearchResults;
import com.schibsted.exercise.indexer.Indexer;
import static com.schibsted.exercise.indexer.Indexer.files;

public class Searcher {

    public static List<SearchResults> searchResults = new ArrayList<SearchResults>();
    private int searchSize;

    public Searcher(List<String> words) throws Exception {
        searchResults.clear();
        searchSize = words.size();
        for (Integer i = 0; i < words.size(); i++) {
            buildResults(searchWord(words.get(i)), i);
        }
        printResults();
    }

    public Set<Integer> searchWord(String _word) {
        Set<Integer> answer = new HashSet<Integer>();
        String word = _word.toLowerCase();

        List<Integer> idx = Indexer.index.get(word);
        if (idx != null) {
            for (Integer i : idx) {
                answer.add(i);
            }
        }
        return answer;
    }

    public void buildResults(Set<Integer> results, int wordOrder) {
        ArrayList<Boolean> presence = new ArrayList<Boolean>();
        presence.clear();

        while (files.size() > searchResults.size()) {
            searchResults.add(new SearchResults("", new ArrayList<Boolean>()));
        }

        for (int i = 0; i < files.size(); i++) {
            presence = searchResults.get(i).words;
            while (searchSize > presence.size()) {
                presence.add(false);
            }
            if (searchResults.get(i).file != files.get(i).file) {
                if (results.contains(i)) {
                    presence.set(wordOrder, true);
                } else {
                    presence.set(wordOrder, false);
                }
            } else {
                if (results.contains(i)) {
                    presence.set(wordOrder, true);
                } else {
                    presence.set(wordOrder, false);
                }
            }
            searchResults.set(i, new SearchResults(files.get(i).file(), presence));
        }
    }

    public void printResults() {
        for (SearchResults search: searchResults) {
            float ocurrences = Collections.frequency(search.words, true);
            System.out.println(search.file + " " + ((ocurrences / searchSize) * 100.0) + "%" );
        }
    }
}