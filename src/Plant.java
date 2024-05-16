//import java.util.TimerTask;

public class Plant extends PlantandZombie {
    private int cost;
    private int range;
    private int cooldown;
    private boolean isStillCooldown = false;
    private int lastattackTime;

    public Plant(String name, int hp, int attack_damage, int attack_speed, int cost, int range, int cooldown, int x, int y){
        super(name, hp, attack_damage, attack_speed, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public void startCooldown() {
        //gatau...
        // Time time = new Time();
        // time.scheduleAtFixedRate(new TimerTask() {
        //     @Override
        //     public void run() {
        //         cooldown--;

        //         if (cooldown == 0) {
        //             time.shutdownNow(); 
        //             status = PlantStatus.Planted;
        //         }
        //     }
        // }, 0, 1000);
    }

    public void takeDamage(Zombie zombie){
        hp -= zombie.getAttackDamage();
    }

    public int setLastAttackTime(int lastAttackTime) {
        this.lastattackTime = lastAttackTime;
    }
    
    public int getLastAttackTime() {
        return lastattackTime;
    }

    public boolean isStillCooldown(){
        return isStillCooldown;
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
