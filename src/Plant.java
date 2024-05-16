//import java.util.TimerTask;

public class Plant extends PlantandZombie {
    private int cost;
    private int range;
    private int cooldown;
    private boolean isStillCooldown = false;

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

    public void displayPlantList(){
        //ganti kata katanya
        System.out.println("Berikut merupakan list plant yang tersedia dengan informasinya");
        for(List<Plant> currentPlant : listPlant){
            System.out.println((i+1) + ". " + "Nama tanaman : " + .getName());
        }
    }
}
