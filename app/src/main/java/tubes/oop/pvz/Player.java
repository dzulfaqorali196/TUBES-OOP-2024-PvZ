package tubes.oop.pvz;

   public class Player{
      private static int sunScore;
   
      public Player(){
         sunScore = 50;
      }
   
      public void increaseSun(int value){
         this.sunScore += value;
      }
   
      public void decreaseSun(int value){
         this.sunScore -= value;
      }

      public static void resetSunScore(){
         sunScore = 50;
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
 