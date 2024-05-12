public abstract class Organism{
   protected String name; 
   protected int hp;
   protected int attack_damage;
   protected int attack_speed;

   public Organism(String name, int hp, int attack_damage, int attack_speed){
      this.name = name;
      this.hp = hp;
      this.attack_damage = attack_damage;
      this.attack_speed = attack_speed;
   }

   public int hit(int damage){
      return damage; // ini keknya masih salah
   }

   public boolean isDead(){
      if (hp <= 0){
         return true;
      } return false;
   }

   public int getHp(){
      return hp;
   }

   public void setHp(int hp){
      this.hp = hp;
   }

   // abstract
   public abstract int attack(int attack_damage);

}
