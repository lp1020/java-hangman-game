package com.penn.ga;
import java.util.ArrayList;


public class GameHistory {

    int totalGames = 0;
    int gamesWon = 0;
    int gamesLost = 0;

    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Integer> wins = new ArrayList<>();
    ArrayList<Integer> losses = new ArrayList<>();



    public String getInitialGameHistory() {
        games.add(totalGames);
        wins.add(gamesWon);
        losses.add(gamesLost);
            System.out.println("Total Games: " + games.toString().substring(1, games.toString().length() - 1));
            System.out.println("Games Won: " + wins.toString().substring(1, wins.toString().length() - 1));
            System.out.println("Games Lost: " + losses.toString().substring(1, losses.toString().length() - 1));
            return "\n";
    }

    }


