package com.penn.ga;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Declare new scanner to scan input
        Scanner scanner = new Scanner(System.in);
        boolean isPlaying = false;
        boolean showHistory = false;
        GameHistory gh = new GameHistory();

        new Welcome();
        String userInput = scanner.nextLine();

        if (userInput.equals("play")) {
            isPlaying = true;
        } else if (userInput.equals("history")) {
            System.out.println("Here's your initial history");
            System.out.println(gh.getInitialGameHistory());
            System.out.println();
            System.out.println("Enter Y to play a game! Enter H for your game history.");
            //handles lower case characters as well as uppercase as input
            Character response = (scanner.next().toUpperCase()).charAt(0);
            isPlaying = (response == 'Y');
            showHistory = (response == 'H');
        } else {
            System.out.println("Invalid input. Select 'play' to play or 'history' for history.");
            new Welcome();
            String userInput2 = scanner.nextLine();
            if (userInput2.equals("play")) {
                isPlaying = true;
            } else if (userInput2.equals("history")) {
                System.out.println("Here's your initial history");
                System.out.println(gh.getInitialGameHistory());
                System.out.println();
                //handles lower case characters as well as uppercase as input
                System.out.println("Enter Y to play another game! Enter H for your game history.");
                Character response = (scanner.next().toUpperCase()).charAt(0);
                isPlaying = (response == 'Y');
                showHistory = (response == 'H');

                }
            }

            //allowing for multiple games
            while (isPlaying) {

                System.out.println("Okay! Let's play!");
                Hangman game = new Hangman();


                do {
                    //Printing out the person
                    System.out.println();
                    System.out.println(game.returnHangman());
                    System.out.println();
                    //getting current guess
                    System.out.println(game.getCurrentGuess());
                    //print out word for testing purposes
                    System.out.println(game.selectedWord);
                    System.out.println();
                    //Retrieve the guess
                    System.out.println("Enter a character");
                    char guess = (scanner.next().toLowerCase()).charAt(0);
                    System.out.println();
                    //Check if the character is guessed already
                    while (game.isGuessed(guess)) {
                        System.out.println("Guess again. You've already guessed that character.");
                        guess = (scanner.next().toLowerCase()).charAt(0);
                    }
                    //try the guess
                    if (game.tryGuess(guess)) {
                        System.out.println("Good job! That character is in the word!");
                    } else {
                        System.out.println("Sorry, that character isn't in the word.");
                    }

                }

                //Keep playing until game is over
                while (!game.gameOver());

                //keep playing
                System.out.println();
                System.out.println("Enter Y to play another game. Enter H for your game history.");
                //handles lower case characters as well as uppercase as input
                Character response = (scanner.next().toUpperCase()).charAt(0);
                isPlaying = (response == 'Y');
                if (response == 'H') {
                    System.out.println(game.getNewGameHistory());
                    //handles lower case characters as well as uppercase as input
                    System.out.println("Enter Y to play another game. Enter H for your game history.");
                    Character response2 = (scanner.next().toUpperCase()).charAt(0);
                    isPlaying = (response2 == 'Y');
                }
            }

        }
    }

