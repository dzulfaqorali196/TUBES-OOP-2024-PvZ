package tubes.oop.pvz;

import java.util.TimerTask;
public class JesterZombie extends Zombie {
    
    public JesterZombie(int x, int y, Map map) {
        super("Jester Zombie", 200, 100, 1, 10000, false, x, y, 0, map);
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
        }, 0, (long) movement_speed); // Zombie bergerak setiap 5 detik
    }

    // @Override
    // public boolean isSlow() {
    //     return false;  // Jester Zombie kebal terhadap efek slow
    // }
}
