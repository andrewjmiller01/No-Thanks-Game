package boardgameproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.scene.image.ImageView;

public class NoThanksPlayer extends Player{
    private List<NoThanksCard> playerCards = new ArrayList<>(0);
    private HashSet<Integer> values = new HashSet<>(0);
    private int numOfChips = 11;
    private List<ImageView> images = new ArrayList<>(0);
    NoThanksPlayer(){
        this("");
    }
    NoThanksPlayer(String n){
        super(n);
        playerCards = new ArrayList<NoThanksCard>();
    }
    public void putChip(NoThanksCard c){
        numOfChips--;
        c.addChip();
    }
    public void grabCard(NoThanksCard c){
        playerCards.add(c);
        images.add(new ImageView(c.getImage()));
        values.add(c.getCardNumber());
        numOfChips += c.getNumOfChips();
    }
    public int getNumberOfCards(){
        return playerCards.size();
    }
    public int getNumberOfChips(){
        return this.numOfChips;
    }
    public int calculateScore(){
        int score = 0;
        for(int i = 0; i < this.getNumberOfCards(); i++){
            if(!values.contains(playerCards.get(i).getCardNumber() - 1)){
                score += playerCards.get(i).getCardNumber();
            }
        }
        return score - this.getNumberOfChips();
    }
    public NoThanksCard[] getPlayerCards(){
        NoThanksCard[] temp = new NoThanksCard[this.getNumberOfCards()];
        for(int i = 0; i < this.getNumberOfCards(); i++){
            temp[i] = this.playerCards.get(i);
        }
        return temp;
    }
    public ImageView[] getImages(){
        ImageView[] temp = new ImageView[this.getNumberOfCards()];
        for(int i = 0; i < this.getNumberOfCards(); i++){
            temp[i] = this.images.get(i);
        }
        return temp;
    }
}