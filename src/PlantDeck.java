import java.util.ArrayList;

public class PlantDeck {
    private ArrayList<Plant> deck;
    
    public PlantDeck(){
        this.deck = new ArrayList<Plant>(6);
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
            deck.remove(index);
        } else {
            throw new InvalidIndexException(index);
        }
    }

    public boolean isDeckEmpty(){
        return deck.size() == 0;
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

    public boolean isPlantCooldown(Plant plant){
        for (Plant currentPlant : deck) {
            if (currentPlant.equals(plant) && currentPlant.isStillCooldown()) {
                return true;
            }
        }
        return false; 
    }

    public void plantPlant(int x, int y){

    }

    public void digPlant(int x, int y){

    }

    public boolean isDeckFull(){
        return totalPlant() == 6;
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