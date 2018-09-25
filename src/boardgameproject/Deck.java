package boardgameproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    protected int numOfCards;
    private List<Card> deck;
    Deck(int i){
        numOfCards = i;
        deck = new ArrayList<>(i);
    }
    public boolean isEmpty(){
        return this.deck.isEmpty();
    }
    public Card drawCard(){
        Random rand = new Random();
        int n = rand.nextInt(numOfCards);
        Card temp = deck.get(n);
        deck.remove(n);
        numOfCards--;
        return temp;
    }
}