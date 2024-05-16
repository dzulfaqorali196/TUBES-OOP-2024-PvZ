package tubes.oop.pvz;
import java.util.List;

public class Inventory {
    private List<Plant> listPlant;

    public Inventory(List<Plant> listPlant) {
        this.listPlant = listPlant;
    }

    public boolean isInventoryEmpty() {
        return listPlant.isEmpty();
    }

    public void displayInventory() {
        int i = 0;
        for(Plant currentPlant : listPlant){
            System.out.println((i+1) + ". Nama tanaman : " + currentPlant.getName());
            System.out.println("Jumlah health : " + currentPlant.getHp());
            System.out.println("Attack damage : " + currentPlant.getAttackDamage());
            System.out.println("Attack speed : " + currentPlant.getAttackSpeed());
            System.out.println("Attack range : " + currentPlant.getRange());
            System.out.println("Time to cooldown : " + currentPlant.getCooldown());
        }
    }

    public boolean isPlantInInventory(Plant plant) {
        return listPlant.contains(plant);
    }

    public Plant getSelectedPlant(int x) {
        if (x >= 0 && x < listPlant.size()) {
            return listPlant.get(x);
        }
        return null;
    }
}