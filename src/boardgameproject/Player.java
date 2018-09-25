package boardgameproject;

public class Player {
    private String name;
    Player(){
        this("");
    }
    Player(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public void changeName(String newName){
        name = newName;
    }
}