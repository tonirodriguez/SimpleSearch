package com.schibsted.exercise.Utils;

public class Tupla {
    public String file;
    public int words;

    /**
     * @param file
     * @param words
     */
    public Tupla(String file, int words) {
        this.file = file;
        this.words = words;
    }

    public String toString() {
        return (this.file + " " + this.words);
    }

    public String file() {
        return this.file;
    }

    public Integer words() {
        return this.words;
    }
}