package tubes.oop.pvz;

   public class Player{
      private static int sunScore;
   
      public Player(int sc){
         sunScore = sc;
      }
   
      public void increaseSun(int value){
         sunScore += value;
      }
   
      public void decreaseSun(int value){
         sunScore -= value;
      }
   
      public static int getSunScore(){
         if(sunScore > 0){
            return sunScore;
         }
         else{
            return 0;
         }
      }
   }
 