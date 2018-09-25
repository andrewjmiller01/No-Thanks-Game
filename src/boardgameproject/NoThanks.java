package boardgameproject;

import java.util.Arrays;

/*
This is the class for the No Thanks game. It extends the Game class.
*/
public class NoThanks extends Game{
    private int numOfPlayers;
    private NoThanksPlayer[] players;
    private int numOfChips;
    private int numOfCards;
    private NoThanksDeck deck;
    NoThanks(){
        this(0);
    }
    NoThanks(int numberOfPlayers){
        super("No Thanks!", 20, 30, 3, 5, numberOfPlayers);
        numOfChips = 55;
        numOfCards = 33;
        deck = new NoThanksDeck(numOfCards);
        numOfPlayers = numberOfPlayers;
        players = new NoThanksPlayer[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++){
            players[i] = new NoThanksPlayer();
        }
    }
    public int numOfCardsLeft(){
        int sum = 0;
        for(int i = 0; i < numOfPlayers; i++){
            sum += players[i].getNumberOfCards();
        }
        return (24 - sum);
    }
    public boolean continuing(){
        return this.numOfCardsLeft() != 0;
    }
    public NoThanksDeck getDeck(){
        return this.deck;
    }
    public NoThanksPlayer[] getPlayers(){
        return this.players;
    }
    public int findLowestScore(int[] s){
        int min = 0;
        for(int i = 1; i < numOfPlayers; i++){
            min = (s[i] < s[min]) ? i : min;
        }
        return min+1;
    }
    public void displayScores(){
        int[] scores = new int[this.numOfPlayers];
        for(int i = 0; i < this.players.length; i++){
            scores[i] = this.players[i].calculateScore();
            System.out.println("Player " + (i + 1) + "'s score is " + scores[i] + ".");
        }
        System.out.println("\nPlayer " + this.findLowestScore(scores) + " is the winner!");
    }
}