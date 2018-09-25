package boardgameproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoThanksDeck extends Deck{
    private List<NoThanksCard> deck;
    NoThanksDeck(){
        this(0);
    }
    NoThanksDeck(int n){
        super(n);
        deck = new ArrayList<>(n);
        this.buildDeck();
    }
    private void buildDeck(){
        for(int i = 3; i <= 35; i++){
            deck.add(new NoThanksCard(i));
        }
        while(deck.size() > 24){
            Random rand = new Random();
            int n = rand.nextInt(deck.size());
            deck.remove(n);
            numOfCards = deck.size();
        }
    }
    @Override
    public Card drawCard(){
        Random rand = new Random();
        int n = rand.nextInt(deck.size());
        NoThanksCard temp = deck.get(n);
        deck.remove(n);
        numOfCards--;
        return temp;
    }
}