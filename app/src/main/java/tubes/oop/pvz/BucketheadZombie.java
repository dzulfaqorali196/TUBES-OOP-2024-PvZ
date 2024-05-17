package tubes.oop.pvz;

public class BucketheadZombie extends Zombie {
    public BucketheadZombie(int x, int y) {
        super("Buckethead Zombie", 300, 100, 1, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}