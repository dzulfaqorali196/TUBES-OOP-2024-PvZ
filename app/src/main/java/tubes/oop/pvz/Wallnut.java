package tubes.oop.pvz;

public class Wallnut extends Barrier{

    public Wallnut(int x, int y) {
        super("Wall-nut", 1000, 0, 0, 50, 0, 20, x, y);
    }

    @Override
    public void armor() {
        System.out.println("Wall-nut go strong!");
    }
}