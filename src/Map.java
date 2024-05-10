public class Map {
    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
        
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 9; x++) {
                tiles[y][x] = new Tile(x, y, "GRASS");
            }
        }
        for (int y = 2; y < 4; y++) {
            for (int x = 0; x <9; x++) {
                tiles[y][x] = new Tile(x, y, "WATER");
            }
        }
        for (int y = 4; y < 6; y++) {
            for (int x = 0; x <9; x++) {
                tiles[y][x] = new Tile(x, y, "GRASS");
            }
        }
    }

    public Tile getTile(int x, int y) throws IndexOutOfBoundsException {
        if (isValidCoordinate(x, y)) {
            return tiles[y][x];
        } else {
            throw new IndexOutOfBoundsException("Invalid coordinates!");
        }
    }
    public void placePlant(Plant plant, int x, int y) throws IllegalStateException, IndexOutOfBoundsException {
        if (isValidCoordinate(x, y)) {
            getTile(x, y).setPlant(plant);
        } else {
            throw new IndexOutOfBoundsException("Invalid coordinates!");
        }
    }

    public void placeZombie(Zombie zombie, int x, int y) throws IllegalStateException, IndexOutOfBoundsException {
        getTile(x, y).setZombie(zombie);
    }

    private boolean isValidCoordinate(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }
}