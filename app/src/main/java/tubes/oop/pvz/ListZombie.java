package tubes.oop.pvz;

import java.util.ArrayList;
import java.util.List;

public class ListZombie {

    private List<Zombie> listZombie;

    public ListZombie() {
        listZombie = new ArrayList<>();
        listZombie.add(new NormalZombie(0, 0));
        listZombie.add(new ConeheadZombie(0, 0));
        listZombie.add(new PoleVaultingZombie(0, 0));
        listZombie.add(new BucketheadZombie(0, 0));
        listZombie.add(new DuckyTubeZombie(0, 0));
        listZombie.add(new DolphinRiderZombie(0, 0));
        listZombie.add(new JesterZombie(0, 0));
        listZombie.add(new ShieldZombie(0, 0));
        listZombie.add(new GiantZombie(0, 0));
        listZombie.add(new FootballZombie(0, 0));
    }

    public List<Zombie> getListZombie(){
        return listZombie;
    }

    public void printZombieInfo() {
        int i = 0;
        for(Zombie currentZombie : getListZombie()){
            System.out.println("----- " + currentZombie.getName() + " -----");
            System.out.println("Nama : " + currentZombie.getName());
            System.out.println("Health : " + currentZombie.getHp());
            System.out.println("Attack damage : " + currentZombie.getAttackDamage());
            System.out.println("Attack speed : " + currentZombie.getAttackSpeed());
            System.out.println("Is Aquatic Zombie : " + currentZombie.getIsAquatic());
            i++;
        }
    }
}
