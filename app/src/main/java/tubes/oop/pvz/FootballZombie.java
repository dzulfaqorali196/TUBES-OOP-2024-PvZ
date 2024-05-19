package tubes.oop.pvz;

import java.util.TimerTask;
public class FootballZombie extends Zombie {
    public FootballZombie(int x, int y, Map map) {
        super("Football Zombie", 500, 100, 2, 10000, false, x, y, 0, map); 
    }

    public void startMoving() {
        moveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isDead() || isAttacking) {
                    return;
                }

                Tile nextTile = map.getNextTile(currentTile);
                if (currentTile.isEmpty()) {
                    if (nextTile.isEmpty()) {
                        move(currentTile, nextTile);
                    } else {
                        startAttacking(nextTile.getPlant());
                    }
                } else {
                    startAttacking(currentTile.getPlant());
                }
            }
        }, 0, isSlow ? 7500 : 5000); // Zombie bergerak setiap 5 detik
    }

}