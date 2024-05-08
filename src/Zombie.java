import java.util.Random;
import java.util.List;
import java.util.Iterator;
// import src.Plant; // Ganti dengan paket yang benar

// Abstract class Zombie
public abstract class Zombie extends Player {
    protected String name;
    protected int hp;
    protected int attack_damage;
    protected int attack_speed;
    protected boolean isAquatic;
    protected int speed;
    protected boolean reachedPlant = false;
    protected boolean isEating = false;
    protected int x;
    protected int y;
    protected int lane;
    // Constructor
    public Zombie(String name, int hp, int attack_damage, int attack_speed, boolean isAquatic, int speed, int x, int y, int lane) {
        //belum konstruk parent class nya
        this.name = name;
        this.hp = hp;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.isAquatic = isAquatic;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.lane = lane;
    }
    
    // Getter for name
    public String getName() { 
        return name; 
    }
    // Getter and Setter for hp
    public void setHp(int newHP){  
        if (newHP >= 0)  
            hp = newHP; 
    }
    public int getHp(){  
        return hp;  
    }

    //getter for attack_damage
    public int getAttackDamage(){
        return attack_damage;
    }

    //getter for attack_speed
    public int getAttackSpeed(){
        return attack_speed;
    }

    //getter for isAquatic
    public boolean getIsAquatic(){
        return isAquatic;
    }

    //getter for speed
    public int getSpeed(){
        return speed;
    }

     // Getters for x and y positions
     public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setter for x position (belum bikin file terpisah untuk throws exceptionnya)
    public void setX(int newX) throws InvalidPositionException {
        if (newX >= 1 && newX < 10) {
          x = newX;
        } else {
          throw new InvalidPositionException("Posisi X zombie tidak valid! Batas map adalah 1 sampai 9.");
        }
      }
      
      public void setY(int newY) throws InvalidPositionException {
        if (newY >= 1 && newY < 7) {
          y = newY;
        } else {
          throw new InvalidPositionException("Posisi Y zombie tidak valid! Batas map adalah 1 sampai 6.");
        }
      }
      
      public int getLane() {
        return lane;
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
                if (p.getRow() == getLane()) {
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

    }

    // Abstract methods
    public abstract void move();
    
    // Ini kayanya perlu buat ngedetermine  random atau tidak
    public static boolean isZombieAppeared() {
        Random random = new Random();
        return random.nextDouble() <= 0.3;
    }
}