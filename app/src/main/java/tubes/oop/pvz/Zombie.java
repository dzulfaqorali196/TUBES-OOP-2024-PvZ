package tubes.oop.pvz;

// import java.util.Random;
// import java.util.List;
// import java.util.Iterator;


public class Zombie extends PlantandZombie {
    private boolean isAquatic;
    protected int jarak;

    public Zombie(String name, int hp, int attack_damage, int attack_speed, boolean isAquatic, int x, int y, int jarak) {
        super(name, hp, attack_damage, attack_speed, x, y);
        this.isAquatic = isAquatic;

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
      
    // menentukan apakah zombie terkena tembakan snowpea
    public  boolean isSlow(){
        return false; //belum terkena
    }
}