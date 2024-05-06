import java.util.Random;

// Abstract class Zombie
public abstract class Zombie {
    protected String name;
    protected int hp;
    protected int attackDamage;
    protected int attackSpeed;
    protected boolean isAquatic;
    protected int speed;
    
    // Constructor
    public Zombie(String name, int hp, int attackDamage, int attackSpeed, boolean isAquatic, int speed) {
        this.name = name;
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.isAquatic = isAquatic;
        this.speed = speed;
    }
    
    // Getter and Setter for hp
    public int getHp() {
        return hp;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    // Abstract methods
    public abstract void eatPlant();
    public abstract void move();
    public abstract boolean isSlow();
    public abstract boolean isAtGoal();
    
    // Ini kayanya perlu buat ngedetermine  random atau tidak
    public static boolean isZombieAppeared() {
        Random random = new Random();
        return random.nextDouble() <= 0.3;
    }
}