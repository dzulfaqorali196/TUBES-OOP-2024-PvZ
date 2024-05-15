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
            i++;
            System.out.println(i + ". " + currentPlant.getName());
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