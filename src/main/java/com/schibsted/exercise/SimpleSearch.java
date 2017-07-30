package com.schibsted.exercise;


import java.io.File;
import java.util.Scanner;

import com.schibsted.exercise.indexer.Indexer;

public class SimpleSearch {

    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            throw new IllegalArgumentException("No directory given to index.");
        }

        final File indexableDirectory = new File(args[0]);
        //TODO: Index all files in indexableDirectory
        new Indexer(args[0]);

        Scanner keyboard = new Scanner(System.in);

        boolean run = true;
        while(run){
            System.out.print("search> ");
            final String line = keyboard.nextLine();
            //TODO: Search indexed files for words in line
            switch (line){
                case "":
                    break;

                case "q":
                    System.out.println("Bye");
                    run = false;
                    break;

                case "h":
                    System.out.println("This is the help");
                    break;

                default:
                    System.out.println("Searching for " + line);
                    break;
            }
        }
    }
}