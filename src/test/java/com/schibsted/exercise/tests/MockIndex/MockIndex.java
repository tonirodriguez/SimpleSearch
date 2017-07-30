package com.schibsted.exercise.tests.MockIndex;

import com.schibsted.exercise.Utils.Tupla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockIndex {

    public static Map<String, List<Integer>> theIndex = new HashMap<String, List<Integer>>();
    public static List<Tupla> theFiles = new ArrayList<Tupla>();

    public MockIndex() {
        theFiles.add(new Tupla("data1.txt", 46));
        theFiles.add(new Tupla("data2.txt", 59));
        theFiles.add(new Tupla("data3.txt", 50));
    }
}
