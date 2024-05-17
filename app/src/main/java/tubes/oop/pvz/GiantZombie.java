package tubes.oop.pvz;

public class GiantZombie extends Zombie {
    public GiantZombie(int x, int y) {
        super("Giant Zombie", 1000, 100, 0.5, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}