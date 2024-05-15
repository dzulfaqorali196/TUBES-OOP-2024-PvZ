public class Lilypad extends Aquatic{
    private Plant plantOnWater;

    public Lilypad(int x, int y){
        super("Lilypad", 100, 0, 0, 25, 0, 10, x, y);
    }

    @Override
    public void water() {
        if(plantOnWater != null){
            System.out.println(plantOnWater.getName() + "is on Lilypad!");
        }
        else{
            System.out.println("Lilypad is On Water, Plant Me please!");
        }
    }

    public void setLilypadPlant(Plant plantOnWater) throws InvalidInputException{
        if (plantOnWater != null) {
            this.setHp(this.getHp() + plantOnWater.getHp());
            plantOnWater.setHp(this.getHp() + plantOnWater.getHp());
        } else {
            throw new InvalidInputException("There is No Plant to Plant");
        }
    }

    public Plant getPlant(){
        return plantOnWater;
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