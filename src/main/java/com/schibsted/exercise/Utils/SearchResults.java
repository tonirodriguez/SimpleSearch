package com.schibsted.exercise.Utils;

import java.util.*;

public class SearchResults {
    public String file;
    public ArrayList<Boolean> words;


    //public SearchResults() {

    //}

    public SearchResults(String file, ArrayList<Boolean> words) {
        this.file = file;
        this.words = words;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setResults(ArrayList<Boolean> words) {
        this.words = words;
    }

    public String toString() {
        return (this.file + " " + this.words);
    }

    public String file() {
        return this.file;
    }

    public ArrayList<Boolean> words() {
        return this.words;
    }
}