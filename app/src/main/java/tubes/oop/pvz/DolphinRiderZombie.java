package tubes.oop.pvz;

public class DolphinRiderZombie extends Zombie {
    private boolean hasJumped = false;

    public DolphinRiderZombie(int x, int y) {
        super("Dolphin Rider Zombie", 175, 100, 1, true, 5, x, y, 0);
    }

    @Override
    public void move() {
        if (!hasJumped) {
            x -= 2 * speed; // Jumps over the plant
            hasJumped = true;
        } else {
            x -= speed;
        }
        if (x < 0) {
            x = 0;
        }
    }
}
