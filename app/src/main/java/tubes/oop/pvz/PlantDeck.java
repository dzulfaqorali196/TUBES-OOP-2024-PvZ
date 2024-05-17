package tubes.oop.pvz;

import java.util.ArrayList;

public class PlantDeck {
    private ArrayList<Plant> deck;
    private Map map;
    
    public PlantDeck(Map map){
        this.deck = new ArrayList<Plant>(6);
        this.map = map;
    }

    public boolean isPlantExist(Plant plant){
        for(Plant currentPlant : deck){
            if(currentPlant.equals(plant)){
                return true;
            }
        }
        return false;
    }

    public void swapPlant(int index1, int index2) throws InvalidIndexException{
        if (index1 >= 0 && index1 < deck.size() && index2 >= 0 && index2 < deck.size()) {
            Plant temp = deck.get(index1);
            deck.set(index1, deck.get(index2));
            deck.set(index2, temp);
        } else {
            if(!(index1 >= 0 && index1 < deck.size())){
                throw new InvalidIndexException(index1);
            }
            else if(!(index2 >= 0 && index2 < deck.size())){
                throw new InvalidIndexException(index2);
            }
        }
    }

    public void removePlant(int index) throws InvalidIndexException{
        if (index >= 0 && index < deck.size()) {
            deck.set(index, null);
        } else {
            throw new InvalidIndexException(index);
        }
    }

    public boolean isDeckEmpty(){
        for(Plant currentPlant : deck){
            if(!(currentPlant.equals(null))){
                return true;
            }
        }
        return true;
    }

    public void addPlant(Plant plant, int index) throws InvalidDeckException, InvalidIndexException{
        if(!isDeckFull()){
            if (index >= 0 && index < deck.size()) {
                deck.add(index, plant); 
            }
            else {
                throw new InvalidIndexException(index);
            }
        }
        else{
            throw new InvalidDeckException("Invalid add plant, deck already full!");
        }
    }

    public int totalPlant(){
        int count = 0;
        for(Plant current : deck){
            if(current != null){
                count++;
            }
        }
        return count;
    }

    public Plant getPlant(int index){
        if (index >= 0 && index < deck.size()) {
            return deck.get(index);
        } else {
            return null;
        }
    }

    public boolean isStillCooldown(Plant plant){
        return ((System.currentTimeMillis() - plant.getLastPlantTime()) <= plant.getCooldown());
    }

    public void plantPlant(int x, int y, Plant plant) {
        Tile tile = map.getTile(x, y);
        if (tile.isEmpty()) {
            if (!isStillCooldown(plant)) {
                tile.setPlant(plant);
                plant.setLastPlantTime(System.currentTimeMillis());
            } else {
                throw new IllegalStateException("Plant is still in cooldown!");
            }
        } else {
            throw new IllegalStateException("Tile already occupied!");
        }
    }

    public void digPlant(int x, int y) {
        Tile tile = map.getTile(x, y);
        if (!tile.isEmpty()) {
            tile.removePlant();
        } else {
            throw new IllegalStateException("No plant to remove!");
        }
    }

    public boolean isDeckFull(){
        for(Plant currenPlant : deck){
            if(currenPlant.equals(null)){
                return false;
            }
        }
        return true;
    }
}

class InvalidIndexException extends java.lang.Exception {
    private int index;

    public InvalidIndexException(int index) {
        this.index = index;
    }

    public String getMessage() {
        return String.format("Invalid index: %s", index);
    }
}

class InvalidDeckException extends java.lang.Exception {
    private String message;

    public InvalidDeckException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}