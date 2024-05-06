package src;
import java.util.Random;
import java.util.List;

// Abstract class Zombie
public abstract class Zombie {
    protected String name;
    protected int hp;
    protected int attack_damage;
    protected int attack_speed;
    protected boolean isAquatic;
    protected int speed;
    protected boolean reachedPlant = false;
    protected boolean isEating = false;

    // Constructor
    public Zombie(String name, int hp, int attack_damage, int attack_speed, boolean isAquatic, int speed) {
        this.name = name;
        this.hp = hp;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.isAquatic = isAquatic;
        this.speed = speed;
    }
    
    // Getter for name
    public String getName() { 
        return this.name; 
    }
    // Getter and Setter for hp
    public void setHp(int newHP){  
        if (newHP >= 0)  
            this.hp = newHP; 
    }
    public int getHp(){  
        return this.hp;  
    }

    //getter for attack_damage
    public int getAttackDamage(){
        return this.attack_damage;
    }

    //getter for attack_speed
    public int getAttackSpeed(){
        return this.attack_speed;
    }

    //getter for isAquatic
    public boolean getIsAquatic(){
        return this.isAquatic;
    }

    //getter for speed
    public int getSpeed(){
        return this.speed;
    }

    // Method untuk menghadle saat zombie memakan tanaman
    public void eatPlant(List<Plant> allPlants) {
        int foundPlant = 0;
        synchronized (allPlants) {
            Iterator<Plant> i = allPlants.iterator();
            while (i.hasNext()) {
                Plant p = i.next();
                // Check if plant is in the same lane
                if (p.getRow() == getLane()) {
                    // Check if zombie is close enough to the plant
                    if (Math.abs(p.getX() - getX()) <= 50) {
                        foundPlant = 1;
                        // Decrease plant's health
                        p.setHp(p.getHp() - this.attack_damage);
                        // Remove plant if its health reaches 0
                        if (p.getHp() <= 0) {
                            i.remove(); // Remove plant from list
                            // Print message indicating the plant has been eaten
                            System.out.println("Plant eaten!");
                        }
                    }
                }
            }
        }
        // If no plant found or zombie moves away from the plant, stop eating
        if (foundPlant == 0) {
            isEating = false;
        }
    }

    //menentukan apakah zombie sudah di akhir tile
    public  boolean isAtGoal(){
        
    }
    // menentukan apakah zombie terkena tembakan snowpea
    public  boolean isSlow(){

    }

    // Abstract methods
    public abstract void move();
    
    // Ini kayanya perlu buat ngedetermine  random atau tidak
    public static boolean isZombieAppeared() {
        Random random = new Random();
        return random.nextDouble() <= 0.3;
    }
}