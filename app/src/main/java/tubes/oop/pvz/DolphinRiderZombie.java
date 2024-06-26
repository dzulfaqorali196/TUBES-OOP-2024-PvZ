package tubes.oop.pvz;

import java.util.TimerTask;

public class DolphinRiderZombie extends Zombie implements SpecialMove {

    private boolean hasJumped;

    public DolphinRiderZombie(int x, int y, Map map) {
        super("Dolphin Rider Zombie", 175, 100, 1, 10000, true, x, y, 0, map);
        this.hasJumped = false;
    }

    @Override
    public void specialMove(Tile currentTile, Tile nextTile) {
        if (!currentTile.isEmpty()) {
            currentTile.removeZombie(this);
            currentTile.removePlant();
            nextTile.setZombie(this);

            this.currentTile = nextTile;
            this.hasJumped = true;

            try {
                setX(getX()-1);
            } catch (InvalidPositionException e) {
            // Handle the exception here
            }
        } else if (!nextTile.isEmpty()) {
            currentTile.removeZombie(this);
            nextTile.removePlant();
            Tile next2Tile = map.getNextTile(nextTile);
            next2Tile.setZombie(this);

            this.currentTile = next2Tile;
            this.hasJumped = true;

            try {
                setX(getX()-2);
            } catch (InvalidPositionException e) {
                // Handle the exception here
            }
        }
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
                            if (!hasJumped) {
                                specialMove(currentTile, nextTile);
                            } else {
                                startAttacking(nextTile.getPlant());
                            }
                        }
                    } else {
                        if (!hasJumped) {
                            specialMove(currentTile, nextTile);
                        } else {
                            startAttacking(currentTile.getPlant());
                        }
                    }
                }
            }, movement_speed, (long) movement_speed); 
        }
    }
}
