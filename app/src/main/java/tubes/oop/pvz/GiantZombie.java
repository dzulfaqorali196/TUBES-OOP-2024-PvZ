package tubes.oop.pvz;

import java.util.TimerTask;
public class GiantZombie extends Zombie {

    public GiantZombie(int x, int y, Map map) {
        super("Giant Zombie", 1000, 100, 1, 10000, false, x, y, 0, map);
    }

    public void startMoving() {
        moveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Tile nextTile = map.getNextTile(currentTile);
                if (!isDead()) {
                    move(currentTile, nextTile);
                    currentTile.removePlant();
                    nextTile.removePlant();
                }
            }
        }, 0, isSlow ? 7500 : 5000); // Zombie bergerak setiap 5 detik
    }
}
