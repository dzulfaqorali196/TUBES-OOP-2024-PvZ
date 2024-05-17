package tubes.oop.pvz;
//import java.util.TimerTask;

public class Plant extends PlantandZombie {
    private int cost;
    private int range;
    private int cooldown;
    private long lastattackTime;
    private long lastPlantTime;

    public Plant(String name, int hp, int attack_damage, int attack_speed, int cost, int range, int cooldown, int x, int y){
        super(name, hp, attack_damage, attack_speed, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public long getLastPlantTime(){
        return lastPlantTime;
    }

    public long setLastPlantTime(long lastPlantTime){
        return this.lastPlantTime = lastPlantTime;
    }

    public void takeDamage(Zombie zombie){
        hp -= zombie.getAttackDamage();
    }

    public void setLastAttackTime(long lastAttackTime) {
        this.lastattackTime = lastAttackTime;
    }
    
    public long getLastAttackTime() {
        return lastattackTime;
    }

    public int getCostPlant(){
        return cost;
    }

    public int getRange(){
        return range;
    }

    public int getCooldown(){
        return cooldown;
    }
}
