package tubes.oop.pvz;

// import java.util.Random;
// import java.util.List;
// import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Zombie extends PlantandZombie {
    private boolean isAquatic;
    protected int jarak;
    private int isSlow;
    Tile currentTile;
    Timer moveTimer;
    Timer attackTimer;
    boolean isAttacking;
    Map map;


    public Zombie(String name, int hp, int attack_damage, int attack_speed, boolean isAquatic, int x, int y, int jarak, Map map) {
        super(name, hp, attack_damage, attack_speed, x, y);
        this.isAquatic = isAquatic;
        isSlow = 0; //0 = gak slow, 1= slow
        this.moveTimer = new Timer();
        this.attackTimer = new Timer();
        this.isAttacking = false;
        this.map = map;

        jarak = 0;
    }

    public int getJarak(){
        return jarak;
    }

    public void setJarak(int jarak){
        this.jarak = jarak;
    }

    public boolean getIsAquatic(){
        return isAquatic;
    }
    
    public void takeDamage(Plant plant) {
        hp -= plant.getAttackDamage();
    }
    
    // public void eatPlant(List<Plant> allPlants) {
    //     boolean foundPlant = false;
    //     synchronized (allPlants) {
    //         Iterator<Plant> i = allPlants.iterator();
    //         while (i.hasNext()) {
    //             Plant p = i.next();
    //             if (p.getX() == getX() && Math.abs(p.getY() - getY()) <= 25) {
    //                 foundPlant = true;
    //                 p.takeDamage(getAttackDamage());
    //                 if (p.getHp() <= 0) {
    //                     i.remove(); // Hilangkan tanaman dari list
    //                     System.out.println("Plant eaten!");
    //                 }
    //             }
    //         }
    //     }
    //     isEating = foundPlant;
    // }

    //menentukan apakah zombie sudah di akhir tile paling kiri 
    public boolean isAtGoal() {
        return x <= 0;  // Hanya periksa posisi x untuk menentukan apakah zombie telah mencapai tujuan
    }

    public void setIsSlow(int slow) {
        this.isSlow = slow ;
    }
      
    // menentukan apakah zombie terkena tembakan snowpea
    public int getIsSlow(){
        return isSlow;
    }

    public void attack(Plant plant) {
        plant.takeDamage(this);
    }

    public void startAttacking(Plant plant) {
        isAttacking = true;
        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (plant.isDead() || isDead()) {
                    attackTimer.cancel();
                    isAttacking = false;
                    if (!isDead()) {
                        // Mulai ulang moveTimer setelah 5 detik
                        moveTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startMoving();
                            }
                        }, 5000); // 5 detik setelah penyerangan selesai
                    }
                } else {
                    attack(plant);
                }
            }
        }, 0, attack_speed);
    }

    public void move(Tile fromTile, Tile toTile) {
        fromTile.removeZombie(this);
        toTile.setZombie(this);
        this.currentTile = toTile;
        try {
            setX(getX()-1);
            setY(getY()-1);
        } catch (InvalidPositionException e) {
            // Handle the exception here
        }
    }

    public abstract void startMoving();


}