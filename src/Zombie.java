import java.util.Random;
import java.util.List;
import java.util.Iterator;


public abstract class Zombie extends PlantandZombie {
    protected boolean isAquatic;
    protected int speed;
    protected boolean reachedPlant = false;
    protected boolean isEating = false;
    protected int x;
    protected int y;
    protected int lane;
    protected int jarak;

    public Zombie(String name, int hp, int attack_damage, int attack_speed, boolean isAquatic, int speed, int x, int y, int lane) {
        super(name, hp, attack_damage, attack_speed, x, y);
        this.isAquatic = isAquatic;
        this.speed = speed;
        this.lane = lane;
        jarak = 0;
        // if(zombieList.isEmpty()){
        //     zombieList.addList();
        //     isHead = true;
        // }
        // else{
        //     isHead = false;
        // }
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

    public int getSpeed(){
        return speed;
    }
      
    public int getLane() {
        return lane;
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
    
      public void setLane(int newLane) {
        // Validasi lane (opsional)
        if (newLane >= 0 && newLane < 6) {
          lane = newLane;
        } else {
          System.out.println("Nomor lane zombie tidak valid! Batas lane adalah 0 sampai 5.");
        }
      }

    // Method untuk menghadle saat zombie memakan tanaman
    public void eatPlant(List<Plant> allPlants) {
        int foundPlant = 0;
        synchronized (allPlants) {
            Iterator<Plant> i = allPlants.iterator();
            while (i.hasNext()) {
                Plant p = i.next();
                // Check apakah tanaman dalam satu lane yang sama dengan zombie
                if (p.getX() == getLane()) {
                    // Check apakah zombie sudah dekat dengan tanaman
                    if (Math.abs(p.getX() - getX()) <= 25) {
                        foundPlant = 1;
                        // Mengurangi darah tanaman
                        p.setHp(p.getHp() - this.attack_damage);
                        // Menghilangkan tanaman jika darah tanaman 0
                        if (p.getHp() <= 0) {
                            i.remove(); // Hilangkan tanaman dari list
                            System.out.println("Plant eaten!");
                        }
                    }
                }
            }
        }
        if (foundPlant == 0) {
            isEating = false;
        }
    }

    //menentukan apakah zombie sudah di akhir tile paling kiri 
    public boolean isAtGoal() {
        // Periksa apakah zombie berada di lane paling kiri (lane 0)
        if (getLane() == 0) {
          // Periksa apakah posisi X zombie kurang dari atau sama dengan 0
          if (getX() <= 0) {
            // Zombie telah mencapai goal
            return true;
          }
        }
        // Zombie belum mencapai goal
        return false;
      }
      
    // menentukan apakah zombie terkena tembakan snowpea
    public  boolean isSlow(){
        return false; //belum
    }

    // Abstract methods
    public abstract void move();
    
    // Ini kayanya perlu buat ngedetermine  random atau tidak
    public static boolean isZombieAppeared() {
        Random random = new Random();
        return random.nextDouble() <= 0.3;
    }

    // BENERIN!! LANENYA JADI X
}