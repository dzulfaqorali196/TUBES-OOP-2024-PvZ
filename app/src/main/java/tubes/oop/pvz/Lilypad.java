package tubes.oop.pvz;

public class Lilypad extends Aquatic{
    public Plant plantOnWater;

    public Lilypad(int x, int y) {
        super("Lilypad", 100, 0, 0, 25, 0, 10000, true, x, y);


    }

    @Override
    public void water() {
        if(plantOnWater != null){
            System.out.println(plantOnWater.getName() + " is on Lilypad!");
        }
        else{
            System.out.println("Lilypad is On Water, Plant Me please!");
        }
    }

    // public void setPlantOnLilypad (Plant plant) {
    //     if (isEmpty()) {
    //         this.plantOnWater = plant;
    //         this.plantOnWater.currentTile = this.currentTile;
    //         // plant.map = map;
    //         System.out.println("x: " + this.getX() + " y: " + this.getY());

    //         // plant.startAttackZombie();

    //         try {
    //             this.plantOnWater.setX(this.getX());
    //             this.plantOnWater.setY(this.getY());
    //         } catch (InvalidPositionException e) {
    //             throw new IllegalStateException("Invalid coordinates!");
    //         }
    //     } else {
    //         throw new IllegalStateException("Tile already occupied!");
    //     }    
    // }

    public void setLilypadPlant(Plant plantOnWater) throws InvalidInputException{
        if (this.plantOnWater == null) {
            
            this.plantOnWater = plantOnWater;
            this.plantOnWater.currentTile = this.currentTile;
            this.setHp(this.getHp() + plantOnWater.getHp());
            // this.attack_damage = plantOnWater.getAttackDamage();
            // this.attack_speed = plantOnWater.getAttackSpeed();
            // this.range = plantOnWater.getRange();
            
            // plantOnWater.setHp(this.getHp() + plantOnWater.getHp());
        } else {
            throw new InvalidInputException("There is No Plant to Plant");
        }
    }

    public Plant getPlant(){
        return this.plantOnWater;
    }

    public boolean isEmpty() {
        return this.plantOnWater == null;
    }

}

class InvalidInputException extends java.lang.Exception {
    private String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}