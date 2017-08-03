package com.schibsted.exercise;

import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.lang.String;

import com.schibsted.exercise.indexer.Indexer;
import com.schibsted.exercise.searcher.Searcher;

public class SimpleSearch {

    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            throw new IllegalArgumentException("No directory given to index.");
        }

        final File directory = new File(args[0]);
        new Indexer(directory);

        Scanner keyboard = new Scanner(System.in);

        boolean run = true;
        while(run){
            System.out.print("search> ");
            final String line = keyboard.nextLine();
            switch (line){
                case "":
                    break;

                case ":q":
                case ":Q":
                    System.out.println("Bye");
                    run = false;
                    break;

                case ":h":
                case ":H":
                    System.out.println("This is the help message, for exit introduce :q");
                    break;

                default:
                    new Searcher(line);
                    break;
            }
        }
    }
}