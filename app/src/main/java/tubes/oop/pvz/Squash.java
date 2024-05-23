package tubes.oop.pvz;

public class Squash extends Bomb{

    public Squash(int x, int y) {
        super("Squash", 100, 5000, 0, 50, -1, 20000, false, x, y);
    }

    @Override
    public void boom() {
        System.out.println("Bomb from Squash!");
    }
}