/*
Name : Justin Alkire

Email : Jalkire@una.edu

Student id: L00737730

Course info: <Cis 315-01>

Program Source file name: Project 3

Due Date: 12/5/2023

Program description: Use the Java techniques to write an application program for 
Summit esports,
We will have to create 3 classes that include one parent class and two child 
classes. The parent
class is the Person class. The child classes are Gamer and TournamentHost. We then 
need to fulfill
ALL the given paramaters as mentioned in the Project3.pdf found on Canvas laid out 
in the directions steps 1-4

Honor code statement: In keeping with the honor code policies of the University of 
University of North Alabama, the
School of Business, and the Department of Computer Science, I affirm that I have 
neither
given nor received on this programming assignment. This assignment
represents my individual, original effort.
... My Signature is on File
*/

// imported with * to import any needed packages 
import java.util.*;

// This class creates a person with variables for a name and address
class Person {
    private String name;
    private String address;

    // This is a constructor to init the name and address
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    //getter
    public String getName() {
        return name;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    //getter
    public String getAddress() {
        return address;
    }

    //setter
    public void setAddress(String address) {
        this.address = address;
    }
}

// Gamer class while also inheriting from Person to get previous properties using extends
class Gamer extends Person {
    private static final int MAX_GAMES = 20;
    private int numGamesPlayed;
    private String[] gameCodes;
    private int[] scores;

    // Constructor to initialize gamer with a name and address
    public Gamer(String name, String address) {
        super(name, address);
        this.numGamesPlayed = 0;
        this.gameCodes = new String[MAX_GAMES];
        this.scores = new int[MAX_GAMES];
    }

    // How we will add GameScore to the gamer
    public void addGameScore(String gameCode, int score) {
        if (numGamesPlayed < MAX_GAMES) {
            gameCodes[numGamesPlayed] = gameCode;
            scores[numGamesPlayed] = score;
            numGamesPlayed++;
        } else {
            System.out.println("Maximum number of games reached for the player.");
        }
    }
    
    // How we print the game score for the gamer
    public void printScore() {
        System.out.println("Game Scores for " + getName() + ":");
        for (int i = 0; i < numGamesPlayed; i++) {
            System.out.println(gameCodes[i] + ": " + scores[i]);
        }
    }

    // How we will calculate and return the average score of a gamer 
    public double getAverageScore() {
        if (numGamesPlayed == 0) {
            return 0;
        }
        int totalScore = 0;
        for (int i = 0; i < numGamesPlayed; i++) {
            totalScore += scores[i];
        }
        return (double) totalScore / numGamesPlayed;  // this is our math on how to do so
    }
}

// Creating a TournamentHost class which extends Person
class TournamentHost extends Person {
    private static final int MAX_GAMES = 5;
    private int numGamesPlaying;
    private String[] gameIDs;

    // Constructor to initialize tournament host with a name and address
    public TournamentHost(String name, String address) {
        super(name, address);
        this.numGamesPlaying = 0;
        this.gameIDs = new String[MAX_GAMES];
    }

    // Adds a game to the tournament, boolean for T/F returns
    public boolean addGame(String gameID) {
        if (numGamesPlaying < MAX_GAMES && !Arrays.asList(gameIDs).contains(gameID)) {
            gameIDs[numGamesPlaying] = gameID;
            numGamesPlaying++;
            System.out.println("Game ID " + gameID + " added.");
            return true;
        } else {
            System.out.println("Cannot add game ID " + gameID + ". It already exists or max limit reached.");
            return false;
        }
    }

    // Removes a game from the tournament, Boolean for T/f retuns
    public boolean removeGame(String gameID) {
        int index = Arrays.asList(gameIDs).indexOf(gameID);
        if (index != -1) {
            System.arraycopy(gameIDs, index + 1, gameIDs, index, numGamesPlaying - index - 1);
            gameIDs[numGamesPlaying - 1] = null;
            numGamesPlaying--;
            System.out.println("Game ID " + gameID + " removed.");
            return true;
        } else {
            System.out.println("Cannot remove game ID " + gameID + ". It was never created.");
            return false;
        }
    }
}

// This is our TestGameData class to test Gamer and TournamentHost classes
public class TestGameData {
    public static void main(String[] args) {
        // Test Gamer class
        Gamer gamer = new Gamer("Justin Alkire", "One Harrison Plaza Florence, AL");
        System.out.println("Gamer: " + gamer.getName() + " at " + gamer.getAddress());
        gamer.addGameScore("Fortnite", 80);
        gamer.addGameScore("Roblox", 90);
        gamer.printScore();
        System.out.println("Average Score: " + gamer.getAverageScore());

        // Test TournamentHost class
        TournamentHost tournamentHost = new TournamentHost("Gamers United", "Seattle, WA");
        System.out.println("Tournament Host: " + tournamentHost.getName() + " at " + tournamentHost.getAddress());

        tournamentHost.addGame("Fortnite");
        tournamentHost.addGame("Roblox");
        tournamentHost.addGame("Call Of Duty");
        tournamentHost.addGame("Fortnite"); // Attempt to add a duplicate game ID
        tournamentHost.removeGame("Roblox");
        tournamentHost.removeGame("Minecraft"); // Attempt to remove a non-existent game
    }
}