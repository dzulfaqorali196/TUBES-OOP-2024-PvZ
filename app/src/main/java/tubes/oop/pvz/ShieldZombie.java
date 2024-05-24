package tubes.oop.pvz;


import java.util.TimerTask;
public class ShieldZombie extends Zombie {
    public ShieldZombie(int x, int y, Map map) {
        super("Shield Zombie", 500, 100, 1, 10000, false, x, y, 0, map);
    }

    public void startMoving() {
        if (getHp() <= 0) {
            currentTile.removeZombie(this);
        } else {
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
            }, movement_speed, movement_speed); 
        }
    }
}