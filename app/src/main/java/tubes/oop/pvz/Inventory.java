package tubes.oop.pvz;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static List<Plant> listPlant;
    

    public Inventory() {
        listPlant = new ArrayList<>();
        listPlant.add(new Sunflower(0, 0));
        listPlant.add(new PeaShooter(0, 0));
        listPlant.add(new Wallnut(0, 0));
        listPlant.add(new SnowPea(0, 0));
        listPlant.add(new Squash(0, 0));
        listPlant.add(new Lilypad(0, 0));
        listPlant.add(new TallWallnut(0, 0));
        listPlant.add(new RepeaterPea(0, 0));
        listPlant.add(new Sunflower(0, 0));
        listPlant.add(new Jalapeno(0, 0));
    }

    public boolean isInventoryEmpty() {
        return listPlant.isEmpty();
    }

    public static void displayInventory() {
        int i = 0;
        for(Plant currentPlant : listPlant){
            System.out.println((i+1) + ". " + currentPlant.getName());
            i++;
        }
    }

    public boolean isPlantInInventory(Plant plant) {
        return listPlant.contains(plant);
    }

    public static Plant getSelectedPlant(int x) {
        if (x >= 0 && x < listPlant.size()) {
            return listPlant.get(x);
        }
        return null;
    }
}