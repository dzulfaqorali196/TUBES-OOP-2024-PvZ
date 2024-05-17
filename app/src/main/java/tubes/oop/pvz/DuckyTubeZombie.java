package tubes.oop.pvz;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie(int x, int y) {
        super("Ducky Tube Zombie", 100, 100, 1, true, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}