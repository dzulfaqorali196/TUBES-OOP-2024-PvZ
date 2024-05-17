package tubes.oop.pvz;

public class PoleVaultingZombie extends Zombie {
    private boolean hasJumped = false;

    public PoleVaultingZombie(int x, int y) {
        super("Pole Vaulting Zombie", 175, 100, 1, false, 5, x, y, 0);
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