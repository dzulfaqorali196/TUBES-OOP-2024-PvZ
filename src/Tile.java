import java.util.List;
import java.util.ArrayList;

public class Tile {
    private final int x;
    private final int y;
    private Plant plant;
    private List<Zombie> zombieList;
    private final String tileType;

    public Tile(int x, int y, String tileType) {
        this.x = x;
        this.y = y;
        this.plant = null;
        this.zombieList = new ArrayList<Zombie>();
        this.tileType = tileType;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Plant getPlant() {
        return plant;
    }

    
    public List<Zombie> getZombie() {
        return zombieList;
    }

    public String getTileType() {
        return tileType;
    }

    // Setters (be cautious modifying x and y)
    public void setPlant(Plant plant) throws IllegalStateException {
        if (isEmpty()) {
            this.plant = plant;
        } else {
            throw new IllegalStateException("Tile already occupied!");
        }
    }

    public void setZombie(Zombie zombie) {
        zombieList.add(zombie);
    }

    public boolean isEmpty() {
        return getPlant()==null;
    }

    public void removePlant() {
        this.plant = null;
    }

    public void removeZombie(Zombie zombie) {
        zombieList.remove(zombie);
    }

    public char getDisplayChar() {
        if (getZombie() != null) {
            return 'Z';
        } else if (!isEmpty()) {
            return 'P';
        } else if (getTileType()=="GRASS") {
            return 'G';
        } else {
            return 'W';
        }
    }


    @Override
    public String toString() {
        String plantStr = plant == null ? "Plant: None" : "Plant: " + plant.getName();
        String zombieStr = zombieList == null ? "Zombie: None" : "Zombie: ";
        if (zombieList != null) {
            for (Zombie zombie : zombieList) {
                zombieStr += zombie.getName() + ", ";
            }
            zombieStr = zombieStr.substring(0, zombieStr.length() - 2); // remove trailing comma and space
        }
        return "Tile (" + x + ", " + y + "): " + plantStr + ", " + zombieStr + ", Type: " + tileType;
    }

    public void move(Map map) {
        for (Zombie zombie : new ArrayList<>(zombieList)) {
            int newCol = zombie.getX() - 1;
            if (newCol < 0) {
                // Zombie reached the leftmost side, game over
                System.out.println("Game Over! Zombie reached the end.");
                System.exit(0);
            }

            Tile nextTile = map.getTile(zombie.getY(), newCol);
            if (nextTile.isEmpty()) {
                removeZombie(zombie);
                nextTile.setZombie(zombie);
                zombie.setX(newCol);
            } else {
                // Attack the plant or wait for the tile to be cleared
            }

            try {
                Thread.sleep(5000); // Delay for MOVE_DELAY seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}