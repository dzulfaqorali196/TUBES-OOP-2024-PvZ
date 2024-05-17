package tubes.oop.pvz;

public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int x, int y) {
        super("Conehead Zombie", 250, 100, 1, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}