import java.util.Random;
import java.util.List;
import java.util.Iterator;

// Class Zombie
public abstract class Zombie extends Organism {
    protected boolean isAquatic;
    protected int speed;
    protected boolean reachedPlant = false;
    protected boolean isEating = false;
    protected boolean isSlow = false;
    protected int slowEndTime; // waktu berakhirnya efek slow
    protected int row; // baris tempat zombie berada
    protected int x; // posisi x zombie

    // Constructor
    public Zombie(String name, int hp, int attack_damage, int attack_speed, boolean isAquatic, int speed, boolean isSlow, int row, int x) {
        super(name, hp, attack_damage, attack_speed);
        this.isAquatic = isAquatic;
        this.speed = speed;
        this.isSlow = isSlow;
        this.slowEndTime = 0; //di awal zombie belum terkena efek slow
        this.row = row;
        this.x = x;
    }

    //getter for isAquatic
    public boolean getIsAquatic(){
        return isAquatic;
    }

    //getter for speed
    public int getSpeed(){
        return speed;
    }

    //getter for row
    public int getRow(){
        return row;
    }

    //setter for row
    public void setRow(int row) {
        this.row = row;
    }

    //getter for x
    public int getX(){
        return x;
    }

    //setter for x
    public void setX(int x) {
        this.x = x;
    }

    // Method untuk mengecek apakah zombie terkena efek slow
    public boolean isSlow() {
      int currentTime = (int) (System.currentTimeMillis() / 1000); // konversi ke detik
      if (currentTime > slowEndTime) {
          this.isSlow = false;
          this.speed = getAttackSpeed(); // kembalikan kecepatan normal
      }
      return this.isSlow;
    }

    // Method untuk mengatur efek slow pada zombie
    public void setSlow(boolean isSlow) {
      this.isSlow = isSlow;
      if (isSlow) {
          this.speed = getAttackSpeed() / 2; // kurangi kecepatan sebesar 50%
          this.slowEndTime = (int) (System.currentTimeMillis() / 1000) + 3; // atur waktu berakhirnya efek slow dalam detik
      } else {
          this.speed = getAttackSpeed(); // kembalikan kecepatan normal
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
                      p.setHp(p.getHp() - getAttackDamage());
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
        // Periksa apakah posisi X zombie kurang dari atau sama dengan 0
        if (getX() <= 0) {
            // Zombie telah mencapai goal
            return true;
        }
        // Zombie belum mencapai goal
        return false;
    }

    // Abstract methods
    public abstract void move();
    
    // Ini kayanya perlu buat ngedetermine  random atau tidak
    public static boolean isZombieAppeared() {
        Random random = new Random();
        return random.nextDouble() <= 0.3;
    }
}
