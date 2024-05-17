package tubes.oop.pvz;

public class ShieldZombie extends Zombie {
    public ShieldZombie(int x, int y) {
        super("Shield Zombie", 500, 100, 1, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}