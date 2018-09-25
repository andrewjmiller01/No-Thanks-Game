package boardgameproject;

import javafx.scene.image.Image;

public class NoThanksCard extends Card{
    private int cardNumber;
    private int numOfChips = 0;
    NoThanksCard(){
        super("");
        cardNumber = 0;
    }
    NoThanksCard(int v){
        super(Integer.toString(v));
        cardNumber = v;
    }
    public int getCardNumber(){
        return cardNumber;
    }
    public int getNumOfChips(){
        return numOfChips;
    }
    public int getCardValue(){
        return this.getCardNumber() - this.getNumOfChips();
    }
    public void setCardNumber(int i){
        this.cardNumber = i;
    }
    public void addChip(){
        this.numOfChips++;
    }
    public Image getImage(){
        return new Image("file:C:\\Users\\Andrew\\Documents\\NetBeansProjects\\BoardGameProject\\src\\boardgameproject\\NoThanks_v2\\card_" + this.getCardNumber() + ".jpg");
    }
}