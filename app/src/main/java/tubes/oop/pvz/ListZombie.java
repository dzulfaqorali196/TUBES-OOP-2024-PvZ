package tubes.oop.pvz;

import java.util.ArrayList;
import java.util.List;

public class ListZombie {

    private List<Zombie> listZombie;

    public ListZombie() {
        listZombie = new ArrayList<>();
        listZombie.add(new NormalZombie(0, 0, null));
        listZombie.add(new ConeheadZombie(0, 0, null));
        listZombie.add(new PoleVaultingZombie(0, 0,null));
        listZombie.add(new BucketheadZombie(0, 0, null));
        listZombie.add(new DuckyTubeZombie(0, 0, null));
        listZombie.add(new DolphinRiderZombie(0, 0, null));
        listZombie.add(new JesterZombie(0, 0, null));
        listZombie.add(new ShieldZombie(0, 0, null));
        listZombie.add(new GiantZombie(0, 0, null));
        listZombie.add(new FootballZombie(0, 0, null));
    }

    public List<Zombie> getListZombie(){
        return listZombie;
    }

    public void printZombieInfo() {
        List<Zombie> zombies = getListZombie();
        
        // Print table header with border
        System.out.println("+-----+----------------------+------------+-----------------+-----------------+----------------------+");
        System.out.printf("| %-3s | %-20s | %-10s | %-15s | %-15s | %-20s |%n", 
            "No", "Nama", "Health", "Attack Damage", "Attack Speed", "Is Aquatic Zombie");
        System.out.println("+-----+----------------------+------------+-----------------+-----------------+----------------------+");
    
        int i = 0;
        for (Zombie currentZombie : zombies) {
            System.out.printf("| %-3d | %-20s | %-10d | %-15d | %-15d | %-20s |%n", 
                i + 1, currentZombie.getName(), currentZombie.getHp(), 
                currentZombie.getAttackDamage(), currentZombie.getAttackSpeed(), 
                currentZombie.getIsAquatic() ? "Yes" : "No");
            System.out.println("+-----+----------------------+------------+-----------------+-----------------+----------------------+");
            i++;
        }
    }
}
