package com.penn.ga;

import java.io.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;
import java.io.IOException;


public class Hangman {

    String selectedWord;
    //keeping track of letters used, mutates characters in the string of selectedWord
    StringBuilder currentGuess;
    //allowing for identical guesses
    ArrayList<Character> prevGuesses = new ArrayList<>();
    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Integer> wins = new ArrayList<>();
    ArrayList<Integer> losses = new ArrayList<>();

    int maxTurns = 6;
    int currentTurn = 0;
    int totalGames = 0;
    int gamesWon = 0;
    int gamesLost = 0;


    //list of available words in wordlist.txt
    ArrayList<String> listOfWords = new ArrayList<>();
    //fileReader gets the wordlist file
    private static FileReader fileReader;
    //bufferedFileReader parses the wordlist file, iterates through the file and takes the selected word from it
    private static BufferedReader bufferedReader;

    public Hangman() {
        initWordList();
        selectedWord = chooseWord();
        //makes the current guess
        currentGuess = initCurrentGuess();

    }

   //initializes list of words array list
    public void initWordList() {
        try {
            //importing file
            File isInFile = new File("wordlist.txt");
            //reads the file
            fileReader = new FileReader(isInFile);
            //parses the file
            bufferedReader = new BufferedReader(fileReader);
            //gets the first line of the word list and returns it
            String wordFromList = bufferedReader.readLine();
            //adding the picked word to the list of words array list
            while(wordFromList != null) {
                listOfWords.add(wordFromList);
                //allows us to go to the next line
                wordFromList = bufferedReader.readLine();
            }
            //closing file readers
            bufferedReader.close();
            fileReader.close();
            //catch error
        } catch(IOException e) {
            System.out.println("Could not initialize words");
        }
    }

    //randomizing choosing the word
    public String chooseWord() {
        Random random = new Random();
        int randomWord = Math.abs(random.nextInt()) % listOfWords.size();
        return listOfWords.get(randomWord);

    }

    //mutating selected string with _ _ _ _ _
    public StringBuilder initCurrentGuess() {
        StringBuilder currentWord = new StringBuilder();
        //doubling length to allow for both dashes and spaces
        for (int i = 0; i < selectedWord.length() * 2; i++) {
            //every other character has a dash
            if (i % 2 == 0) {
                currentWord.append("_");
            } else {
                currentWord.append(" ");
            }
            //otherwise, return the word
        } return currentWord;
    }
    //output is _ _ b _ _ h
    public String getCurrentGuess() {
        return "Current Guess: " + currentGuess.toString();
    }
    //game over logic
    public boolean gameOver() {
        if (isWon()) {
            System.out.println("");
            System.out.println("Congrats! You won!");
            gamesWon++;
            totalGames++;
            wins.add(gamesWon);
            games.add(totalGames);
            return true;

        } else if (isLost()) {
            System.out.println();
            System.out.println("Sorry, you lost. You have lost all of your turns. The word was " + selectedWord + ".");
            gamesLost++;
            totalGames++;
            losses.add(gamesLost);
            games.add(totalGames);
            return true;
        }
        return false;
    }
    //losing condition
    public boolean isLost() {
        return currentTurn >= maxTurns;
    }
    //winning condition
    public boolean isWon() {
        String guess = currentGuessNoSpaces();
        return guess.equals(selectedWord);
    }

    //takes out spaces
    public String currentGuessNoSpaces() {
        String guess = currentGuess.toString();
        return guess.replace(" ", "");
    }
    //checks for repeated character inputs
    public boolean isGuessed(char character) {
       return prevGuesses.contains(character);
    }
    //replaces dash with character if character is in the word
    public boolean tryGuess(char character) {
        boolean isCorrect = false;
        prevGuesses.add(character);
        for (int i = 0; i < selectedWord.length(); i ++) {
            if (selectedWord.charAt(i) == character) {
                currentGuess.setCharAt(i * 2, character);
                isCorrect = true;
            }
        }

        if (!isCorrect) {
            currentTurn++;
        }

        return isCorrect;

    }

    //printing out hangman picture to console
    public String returnHangman() {
        switch (currentTurn) {
            case 0: return noPerson();
            case 1: return addHead();
            case 2: return addBody();
            case 3: return addFirstArm();
            case 4: return addSecondArm();
            case 5: return addFirstLeg();
            default: return fullPerson();
        }
    }


    private String noPerson() {
        return  " _________\n" +
                "|        |\n" +
                "|        \n" +
                "|         \n" +
                "|         \n" +
                "|          \n" +
                "|\n" +
                "|\n";

    }

    private String addHead() {
        return " _________\n" +
                "|        |\n" +
                "|        O\n" +
                "|           \n" +
                "|        \n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addBody() {
        return " _________\n" +
                "|        |\n" +
                "|        O\n" +
                "|        |  \n" +
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addFirstArm() {
        return " _________\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / |  \n" +
                "|        |\n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }

    private String addSecondArm() {
        return " _________\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / | \\ \n" +
                "|        |\n" +
                "|          \n" +
                "|\n" +
                "|\n";
    }

    private String addFirstLeg() {
        return " _________\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / | \\ \n" +
                "|        |\n" +
                "|       /  \n" +
                "|\n" +
                "|\n";
    }

    private String fullPerson() {
        return " _________\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / | \\ \n" +
                "|        |\n" +
                "|       / \\ \n" +
                "|\n" +
                "|\n";
    }

    //getting updated game history
    public String getNewGameHistory() {
        System.out.println("Total Games: " + games.toString().substring(1, games.toString().length() - 1));
        System.out.println("Games Won: " + wins.toString().substring(1, wins.toString().length() - 1));
        System.out.println("Games Lost: " + losses.toString().substring(1, losses.toString().length() - 1));
        return "\n";
    }




}
