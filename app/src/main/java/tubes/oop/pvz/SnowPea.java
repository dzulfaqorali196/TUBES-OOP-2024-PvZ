package tubes.oop.pvz;

public class SnowPea extends Shooter{

    public SnowPea(int x, int y) {
        super("Snow Pea", 100, 25, 4, 175, -1, 20, x, y);
    }

    @Override
    public void shoot() {
        System.out.println("Snow Pea is Shooting!");
    }
}