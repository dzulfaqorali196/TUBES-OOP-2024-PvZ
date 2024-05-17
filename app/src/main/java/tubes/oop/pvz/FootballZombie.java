package tubes.oop.pvz;

public class FootballZombie extends Zombie {
    public FootballZombie(int x, int y) {
        super("Football Zombie", 500, 100, 2, false, 5, x, y, 0);
    }

    @Override
    public void move() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }
}