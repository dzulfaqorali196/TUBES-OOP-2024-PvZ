package tubes.oop.pvz;
//import java.util.TimerTask;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Plant extends PlantandZombie {
    public boolean isAquatic;
    private int cost;
    public int range;
    private long cooldown;
    private long lastattackTime;
    private long lastPlantTime;
    // Map map;
    Tile currentTile;
    Timer moveTimer;
    Time cooldownPlant;


    public Plant(String name, int hp, int attack_damage, int attack_speed, int cost, int range, long cooldown, boolean isAquatic, int x, int y){
        super(name, hp, attack_damage, attack_speed, x, y);
        this.cost = cost;
        this.range = range;
        // this.map = map;

        this.cooldown = cooldown;
    }

    public long getLastPlantTime(){
        return lastPlantTime;
    }
    
    public boolean getIsAquatic(){
        return this.isAquatic;
    }

    public long setLastPlantTime(long lastPlantTime){
        return this.lastPlantTime = lastPlantTime;
    }

    public void takeDamage(Zombie zombie){
        this.hp -= zombie.getAttackDamage();
    }

    public void setLastAttackTime(long lastAttackTime) {
        this.lastattackTime = lastAttackTime;
    }
    
    public long getLastAttackTime() {
        return this.lastattackTime;
    }

    public int getCostPlant(){
        return this.cost;
    }

    public int getRange(){
        return this.range;
    }

    public long getCooldown(){
        return this.cooldown;
    }


    // public abstract void attack();
    // public void getZombieInRange(Map map) {
    //     int plantRange = this.getRange();
    //     int plantX = this.getX();
    //     int plantY = this.getY();
    //     int zombieTerdekat;
    //     int tanda = 10 ;

        
    //     if (plantRange == -1) {
    //         for ( int i = plantX; i<=10; i++) {
    //             if (!map.getTile(i, plantY).getZombie().isEmpty()) {
    //                 List<Zombie> zombies = map.getTile(i, plantY).getZombie();
    //                 for (Zombie zombieterdekat : zombies) {
    //                     if (zombieterdekat.getHp()<=0) {
    //                         map.getTile(i, plantY).removeZombie(zombieterdekat);
    //                     } else {
    //                         if (this.getName().equals("Snow Pea")) {
    //                             if (zombieterdekat.getName().equals("Jester Zombie")) {
    //                                 zombieterdekat.takeDamage(this.getAttackDamage());
    //                             } else {
    //                                 zombieterdekat.takeDamage(this.getAttackDamage());
    //                                 zombieterdekat.applySnowPeaEffect();
    //                             }
    //                         } else {
    //                             zombieterdekat.takeDamage(this.getAttackDamage());
    //                         }
                                
    //                     }
    //                 }

    //             }
    //         }

    //         // for (int j = 10; j >= plantX; j--) {
    //         //     if (map.getTile(j, plantY).getZombie() != null) {
    //         //         if (j<tanda) {
    //         //             tanda = j;
    //         //         }
    //         //     }
    //         // }

    //         // zombieTerdekat = tanda;
    //         // List<Zombie> zombies = map.getTile(zombieTerdekat, plantY).getZombie();
    //         // if (zombies != null) {
    //         //     for (Zombie zombieterdekat : zombies) {
    //         //         if (this.getName().equals("Snow Pea")) {
    //         //             if (zombieterdekat.getName().equals("Jester Zombie")) {
    //         //                 zombieterdekat.takeDamage(this.getAttackDamage());
    //         //             } else {
    //         //                 zombieterdekat.applySnowPeaEffect();
    //         //                 zombieterdekat.takeDamage(this.getAttackDamage());
    //         //             }
    //         //         } else {
    //         //             zombieterdekat.takeDamage(this.getAttackDamage());
    //         //         }
    //         //     }
    //         // }
    //         // for (Zombie zombieterdekat : map.getTile(zombieTerdekat, plantY).getZombie()) {
    //         //     if (this.getName()=="Snow Pea") {
    //         //         if (zombieterdekat.getName()=="Jester Zombie") {
    //         //             zombieterdekat.takeDamage(attack_damage);
    //         //         } else {
    //         //             zombieterdekat.applySnowPeaEffect();
    //         //             zombieterdekat.takeDamage(attack_damage);
    //         //         }
    //         //     } else {
    //         //         zombieterdekat.takeDamage(attack_damage);
    //         //     }
    //         // }
            
    //     } else if (plantRange == 1) {
    //         if (map.getTile(plantX, plantY).getZombie() != null) {
    //             for (Zombie zombie : map.getTile(plantX, plantY).getZombie()) {
    //                 zombie.takeDamage(attack_damage);
    //             }

    //         } else if (map.getTile(plantX+1, plantY).getZombie() != null) {
    //             for (Zombie zombie : map.getTile(plantX +1, plantY).getZombie()) {
    //                 zombie.takeDamage(attack_damage);
    //             }
    //         }
    //     }
        
    // }

    // public void startAttackZombie() {
    //     Timer moveTimer = new Timer();
    //     moveTimer.scheduleAtFixedRate(new TimerTask() {

    //         @Override
    //         public void run() {
    //             getZombieInRange();
    //         }
    //     }, 0, 4);
    // }
}
