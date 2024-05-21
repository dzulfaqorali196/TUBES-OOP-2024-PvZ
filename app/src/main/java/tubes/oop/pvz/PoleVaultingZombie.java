package tubes.oop.pvz;

import java.util.TimerTask;
public class PoleVaultingZombie extends Zombie implements SpecialMove {

    private boolean hasJumped;

    public PoleVaultingZombie(int x, int y, Map map) {
        super("Pole Vaulting Zombie", 175, 100, 1, 100000, false, x, y, 0, map);
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
                    if (getHp() <= 0 || isAttacking) {
                        return;
                    }
    
    
                    if (currentTile.isEmpty()) {
                        if (map.getNextTile(currentTile).isEmpty()) {
                            move(currentTile, map.getNextTile(currentTile));
                        } else {
                            if (!hasJumped) {
                                specialMove(currentTile, map.getNextTile(currentTile));
                            } else {
                                startAttacking(map.getNextTile(currentTile).getPlant());
                            }
                        }
                    } else {
                        if (!hasJumped) {
                            specialMove(currentTile, map.getNextTile(currentTile));
                        } else {
                            startAttacking(currentTile.getPlant());
                        }
                    }
                }
            }, 0, movement_speed); 
        }
        
    }
}
