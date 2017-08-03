package com.schibsted.exercise.Utils;

import java.lang.String;

public class Cleaner {

    private String input;
    public String[] noSpecialChars;

    public Cleaner(String input) {
        this.input = input;
        this.noSpecialChars = splitAndRemoveSpecialChars();
    }

    private String[] splitAndRemoveSpecialChars() {
        String[] splited = input.split("\\s+");
        for(int i=0; i < splited.length; i++) {
            splited[i] = splited[i].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        }
        this.noSpecialChars = splited;
        return this.noSpecialChars;
    }
}
