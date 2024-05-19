package tubes.oop.pvz;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Plant> listPlant;
    
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

    public List<Plant> getListPlant(){
        return listPlant;
    }

    public void displayInventory() {
        int i = 0;
        for(Plant currentPlant : listPlant){
            System.out.println((i+1) + ". " + currentPlant.getName());
            i++;
        }
    }

    public void printPlantInfo() {
        int i = 0;
        for(Plant currentPlant : getListPlant()){
            System.out.println("----- " + currentPlant.getName() + " -----");
            System.out.println("Nama : " + currentPlant.getName());
            System.out.println("Health : " + currentPlant.getHp());
            System.out.println("Attack damage : " + currentPlant.getAttackDamage());
            System.out.println("Attack speed : " + currentPlant.getAttackSpeed());
            System.out.println("Cost : " + currentPlant.getCostPlant());
            System.out.println("Range : " + currentPlant.getRange());
            System.out.println("Cooldown : " + currentPlant.getCooldown());
            i++;
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

    public void swapPlantInventory(int index1, int index2) throws InvalidIndexException{
        if (index1 >= 0 && index1 < listPlant.size() && index2 >= 0 && index2 < listPlant.size()) {
            Plant temp = listPlant.get(index1);
            listPlant.set(index1, listPlant.get(index2));
            listPlant.set(index2, temp);
        } else {
            if(!(index1 >= 0 && index1 < listPlant.size())){
                throw new InvalidIndexException(index1);
            }
            else if(!(index2 >= 0 && index2 < listPlant.size())){
                throw new InvalidIndexException(index2);
            }
        }
    }
}