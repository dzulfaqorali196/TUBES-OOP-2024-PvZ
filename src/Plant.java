//import java.util.TimerTask;

public class Plant extends PlantandZombie {
    private int cost;
    private int range;
    private int cooldown;
    private PlantStatus status;
    private boolean isStillCooldown = false;

    public Plant(String name, int hp, int attack_damage, int attack_speed, int cost, int range, int cooldown, int x, int y){
        super(name, hp, attack_damage, attack_speed, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.status = PlantStatus.Planted;
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

    public void takeDamage(){
        // BENERIN!!
        // if(){
        // for(int i = 0; i<= zombieList.size(); i++){
        //     for(int j = 0; j<= zombieList.size(); j++){

        //     }
        // }
        // }
    }

    public boolean isStillCooldown(){
        return isStillCooldown;
    }

    public PlantStatus getStatus() {
        return status;
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

    public enum PlantStatus {
        WaitCooldown, WaitPlanted, Planted
    }
}
