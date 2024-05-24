package tubes.oop.pvz;

import java.util.TimerTask;
public class JesterZombie extends Zombie {
    
    public JesterZombie(int x, int y, Map map) {
        super("Jester Zombie", 200, 100, 1, 10000, false, x, y, 0, map);
    }

    public void startMoving() {
        if (getHp() <= 0) {
            currentTile.removeZombie(this);
        } else {
            moveTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (isDead() || isAttacking) {
                        moveTimer.cancel();
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
            }, movement_speed, movement_speed); 
        }
        
    }

    // @Override
    // public boolean isSlow() {
    //     return false;  // Jester Zombie kebal terhadap efek slow
    // }
}
