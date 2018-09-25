package boardgameproject;
/*
This class will be the super class from which all of the different games will be created.
*/
public class Game {
    private String name;
    private int minTime;
    private int maxTime;
    private int minPlayers;
    private int maxPlayers;
    private int numOfPlayers;
    Game(){
        this("", 0, 0, 0, 0, 0);
    }
    Game(String n, int minT, int maxT, int minP, int maxP, int p){
        name = n;
        minTime = minT;
        maxTime = maxT;
        minPlayers = minP;
        maxPlayers = maxP;
        numOfPlayers = p;
    }
    public String getName(){
        return this.name;
    }
    public int getMinTime(){
        return this.minTime;
    }
    public int getMaxTime(){
        return this.maxTime;
    }
    public int getMinPlayers(){
        return this.minPlayers;
    }
    public int getMaxPlayers(){
        return this.maxPlayers;
    }
    public int getNumOfPlayers(){
        return this.numOfPlayers;
    }
    public String playerRange(){
        return this.getMinPlayers() + " - " + this.getMaxPlayers();
    }
    public String timeRange(){
        return this.getMinTime() + " - " + this.getMaxTime();
    }
}