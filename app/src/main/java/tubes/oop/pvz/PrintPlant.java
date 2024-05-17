package tubes.oop.pvz;

public class PrintPlant{

    public static void printPlantInfo(Plant plant) {
        
        System.out.println("Nama : " + plant.getName());
        System.out.println("Health : " + plant.getHp());
        System.out.println("Attack damage : " + plant.getAttackDamage());
        System.out.println("Attack speed : " + plant.getAttackSpeed());
        System.out.println("Cost : " + plant.getCostPlant());
        System.out.println("Range : " + plant.getRange());
        System.out.println("Cooldown : " + plant.getCooldown());

        System.out.println();
    }
}
