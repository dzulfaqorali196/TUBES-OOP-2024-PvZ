public class Player{
    private static int sunScore = 25;
 
    public Player(int sunScore){
       Player.sunScore = sunScore;
    }
 
    public static void increaseSun(){
       sunScore += 25;
    }
 
    public void decreaseSun(int value){
       sunScore -= value;
    }
 
    public int getSunScore(){
       return sunScore;
    }
 }
 