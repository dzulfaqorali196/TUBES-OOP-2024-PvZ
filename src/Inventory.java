import java.util.List;

public class Inventory {
    private List<Plant> slotPlant;

    public Inventory(List<Plant> slotPlant) {
        this.slotPlant = slotPlant;
    }

    public boolean isInventoryEmpty() {
        return slotPlant.isEmpty();
    }

    public void displayInventory(int index) {
        if (index >= 0 && index < slotPlant.size()) {
            Plant plant = slotPlant.get(index);
            System.out.println("Plant at index " + index + ": " + plant.getName());
        } else {
            System.out.println("No plant at index " + index);
        }
    }

    public boolean isInventoryFull() {
        return slotPlant.size() >= 5;
    }

    public void clearInventory() {
        slotPlant.clear();
    }

    public boolean isPlantInInventory(Plant plant) {
        return slotPlant.contains(plant);
    }

    public boolean isPlantSwitch(int x, int y) {
        if (x < 0 || y < 0 || x >= slotPlant.size() || y >= slotPlant.size() || x == y) {
            return false;
        }
        // Swap plants at index x and y
        Plant temp = slotPlant.get(x);
        slotPlant.set(x, slotPlant.get(y));
        slotPlant.set(y, temp);
        return true;
    }

    public boolean isPlantCanDelete(int x) {
        return x >= 0 && x < slotPlant.size();
    }

    public Plant getSelectedPlant(int x) {
        if (x >= 0 && x < slotPlant.size()) {
            return slotPlant.get(x);
        }
        return null;
    }
}
