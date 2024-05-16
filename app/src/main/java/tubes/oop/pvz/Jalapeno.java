package tubes.oop.pvz;

public class Jalapeno extends Bomb{

    public Jalapeno(int x, int y){
        super("Jalapeno", 100, 5000, 0, 75, -1, 20, x, y);
    }

    @Override
    public void boom() {
        System.out.println("Bomb from Jalapeno!");
    }
}