package tubes.oop.pvz;

public class NormalZombie extends Zombie {
    public NormalZombie(int x, int y) {
        super("Normal Zombie", 125, 100, 1, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}