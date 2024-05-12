public abstract class Organism{
   protected String name; 
   protected int health;
   protected int attack_damage;
   protected int attack_speed;

   public Organism(String name, int health, int attack_damage, int attack_speed){
      this.name = name;
      this.health = health;
      this.attack_damage = attack_damage;
      this.attack_speed = attack_speed;
   }

   public int hit(int damage){
      return damage; // ini keknya masih salah
   }

   public boolean isDead(){
      if (health <= 0){
         return true;
      } return false;
   }

   public int gethealth(){
      return health;
   }

   public void sethealth(int health){
      this.health = health;
   }

   // abstract
   public abstract int attack(int attack_damage);

}
