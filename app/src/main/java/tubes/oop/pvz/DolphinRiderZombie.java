package tubes.oop.pvz;

import java.util.TimerTask;

public class DolphinRiderZombie extends Zombie implements SpecialMove {

    private boolean hasJumped;

    public DolphinRiderZombie(int x, int y, Map map) {
        super("Dolphin Rider Zombie", 175, 100, 1, true, x, y, 0, map);
        this.hasJumped = false;
    }

    @Override
    public void specialMove(Tile currentTile, Tile nextTile) {
        currentTile.removeZombie(this);
        currentTile.removePlant();
        nextTile.removePlant();
        Tile next2Tile = map.getNextTile(nextTile);
        next2Tile.setZombie(this);

        this.currentTile = next2Tile;
        hasJumped = true;

        try {
            setX(getX()-2);
            setY(getY()-2);
        } catch (InvalidPositionException e) {
            // Handle the exception here
        }
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
        }, 0, 5000); // Zombie bergerak setiap 5 detik
    }
}
