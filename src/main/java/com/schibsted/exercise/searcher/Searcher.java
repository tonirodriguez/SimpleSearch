package com.schibsted.exercise.searcher;

import java.util.*;

import com.schibsted.exercise.Utils.SearchResults;
import com.schibsted.exercise.Utils.Cleaner;
import com.schibsted.exercise.indexer.Indexer;
import static com.schibsted.exercise.indexer.Indexer.files;

public class Searcher {

    public static List<SearchResults> searchResults = new ArrayList<SearchResults>();
    private int searchSize;
    private Cleaner cleaner = new Cleaner();

    public Searcher(String words) throws Exception {
        searchResults.clear();
        String[] searchString = cleaner.splitAndRemoveSpecialChars(words);
        searchSize = searchString.length;

        System.out.print("Searching for: ");
        for (Integer i = 0; i < searchString.length; i++) {
            System.out.print(searchString[i] + " ");
            buildResults(searchWord(searchString[i]), i);
        }
        System.out.println("");
        printResults();
    }

    public Set<Integer> searchWord(String word) {
        Set<Integer> answer = new HashSet<Integer>();
        //String word = _word.toLowerCase();

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
            double rank = calculateRank(search);
            System.out.println(search.file + " " + String.format("%.4f", rank) + "%" );
            }
    }

    public double calculateRank(SearchResults search) {
        double occurrences = Collections.frequency(search.words, true);
        double result = ((occurrences / searchSize) * 100d);

        return result;

    }
}