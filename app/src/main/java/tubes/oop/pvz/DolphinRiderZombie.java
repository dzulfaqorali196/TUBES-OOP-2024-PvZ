package tubes.oop.pvz;

public class DolphinRiderZombie extends Zombie {
    private boolean hasJumped = false;

    public DolphinRiderZombie(int x, int y) {
        super("Dolphin Rider Zombie", 175, 100, 1, true, x, y, 0);
    }
}
