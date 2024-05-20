package tubes.oop.pvz;

import java.util.TimerTask;
public class NormalZombie extends Zombie {
    public NormalZombie(int x, int y, Map map) {
        super("Normal Zombie", 125, 100, 1, 10000, false, x, y, 0, map);
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
        }, 0, movement_speed); 
    }
}