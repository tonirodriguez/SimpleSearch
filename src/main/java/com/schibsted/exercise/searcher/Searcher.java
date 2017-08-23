package com.schibsted.exercise.searcher;

import java.util.*;

import com.schibsted.exercise.Utils.SearchResults;
import com.schibsted.exercise.Utils.Cleaner;
import com.schibsted.exercise.Utils.StopWords;
import com.schibsted.exercise.indexer.Indexer;
import static com.schibsted.exercise.indexer.Indexer.files;

public class Searcher {

    public static List<SearchResults> searchResults = new ArrayList<SearchResults>();
    private static int searchSize;
    private static int invalidWords = 0;
    private Cleaner cleaner = new Cleaner();
    private List<String> stopWords = new StopWords().stopwords;

    public Searcher(String words) throws Exception {
        invalidWords = 0;
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
        if (stopWords.contains(word)) {
            invalidWords++;
        } else {
            List<Integer> idx = Indexer.index.get(word);
            if (idx != null) {
                for (Integer i : idx) {
                    answer.add(i);
                }
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
        HashMap<String, Double> map = new HashMap<String, Double>();
        ValueComparator vc = new ValueComparator(map);
        TreeMap<String, Double> sortedResult = new TreeMap<String, Double>(vc);

        for (SearchResults search : searchResults) {
            map.put(search.file, calculateRank(search));
        }

        sortedResult.putAll(map);

        int results = 0;
        for(Map.Entry<String,Double> entry : sortedResult.entrySet()) {
            if (results < 10) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                results++;
            } else {
                continue;
            }
        }
    }

    public static double calculateRank(SearchResults search) {
        double occurrences;
        double result = 0;
        if (invalidWords != searchSize) {
            occurrences = Collections.frequency(search.words, true);
            result = ((occurrences / (searchSize - invalidWords)) * 100d);
        }
        return result;
    }

    private class ValueComparator implements Comparator<String> {
        Map<String, Double> base;

        public ValueComparator(Map<String, Double> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}