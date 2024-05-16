package tubes.oop.pvz;

public class SnowPea extends Shooter{

    public SnowPea(int x, int y){
        super("Snow Pea", 100, 25, 4000, 175, 0, 20, x, y);
    }

    @Override
    public void shoot() {
        System.out.println("Snow Pea is Shooting!");
    }
}