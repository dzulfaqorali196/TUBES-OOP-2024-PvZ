package tubes.oop.pvz;

public class JesterZombie extends Zombie {
    public JesterZombie(int x, int y) {
        super("Jester Zombie", 200, 100, 0.5, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    @Override
    public boolean isSlow() {
        return false; // Jester Zombie is immune to slowing effects
    }
}
