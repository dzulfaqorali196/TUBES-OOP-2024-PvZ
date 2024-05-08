public abstract class Player{
   private static int sunScore = 25;

   public Player(int sunScore){
      Player.sunScore = sunScore;
   }

   public int hit(int damage){
      return damage; // ini keknya masih salah
   }

   public boolean isDead(int health){
      if (health <= 0){
         return true;
      } return false;
   }

   public abstract int attack(int attack_damage);

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
