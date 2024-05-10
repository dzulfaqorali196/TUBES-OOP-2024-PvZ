public class Tile {
    private final int x;
    private final int y;
    private Plant plant;
    private Zombie zombie;
    private final String tileType;

    public Tile(int x, int y, String tileType) {
        this.x = x;
        this.y = y;
        this.plant = null;
        this.zombie = null;
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

    public Zombie getZombie() {
        return zombie;
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
        this.zombie = zombie;
    }

    public boolean isEmpty() {
        return getPlant()==null;
    }

    public void removePlant() {
        this.plant = null;
    }

    public void removeZombie() {
        this.zombie = null;
    }

    @Override
    public String toString() {
        String plantStr = plant == null ? "Plant: None" : "Plant: " + plant.getName();
        String zombieStr = zombie == null ? "Zombie: None" : "Zombie: " + zombie.getName();
        return "Tile (" + x + ", " + y + "): " + plantStr + ", " + zombieStr + ", Type: " + tileType;
    }
}