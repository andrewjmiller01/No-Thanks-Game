package boardgameproject;

public class Card {
    private String name;
    Card(){
        this("");
    }
    Card(String n){
        name = n;
    }
    public String getName(){
        return this.name;
    }
}