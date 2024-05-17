package tubes.oop.pvz;

public class PrintZombie {

    public static void printZombieInfo(Zombie zombie) {
        
        System.out.println("Nama : " + zombie.getName());
        System.out.println("Health : " + zombie.getHp());
        System.out.println("Attack damage : " + zombie.getAttackDamage());
        System.out.println("Attack speed : " + zombie.getAttackSpeed());
        System.out.println("Is Aquatic : " + zombie.getIsAquatic());

        System.out.println();
    }
}
