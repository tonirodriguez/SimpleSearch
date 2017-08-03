package com.schibsted.exercise.Utils;

import java.lang.String;

public class Cleaner {

    public Cleaner() {
    }

    public String[] splitAndRemoveSpecialChars(String input) {
        String[] splited = input.split("\\s+");
        for(int i=0; i < splited.length; i++) {
            splited[i] = splited[i].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        }
        return splited;
    }
}
