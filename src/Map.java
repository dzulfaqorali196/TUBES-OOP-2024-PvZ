import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private final int width;
    private final int height;
    private final Tile[][] tiles;
    private List<Integer> numberZombie;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
        numberZombie = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        
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

    public static String getRandomZombie(ArrayList<Integer> numberZombie, double probability){
        // BENERIN!!!
        return "x"; //ubah aja
    }

    private boolean isValidCoordinate(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }

    public void printMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(tiles[height][width].getDisplayChar() + " ");
            }
            System.out.println();
        }
    }
    // BENERIN!!
    /*
     * public void printPlantsInRow(int row) {
        if (row < 0 || row >= height) {
            System.out.println("Baris tidak valid!");
            return;
        }
        System.out.print("Tanaman di baris " + row + ": ");
        for (int x = 0; x < width; x++) {
            Plant plant = tiles[row][x].getPlant();
            if (plant != null) {
                System.out.print(plant.getName() + " ");
            }
        }
        System.out.println();
    }

    public void printZombiesInRow(int row) {
        if (row < 0 || row >= height) {
            System.out.println("Baris tidak valid!");
            return;
        }
        System.out.print("Zombie di baris " + row + ": ");
        for (int x = 0; x < width; x++) {
            List<Zombie> zombies = tiles[row][x].getZombies();
            if (!zombies.isEmpty()) {
                for (Zombie zombie : zombies) {
                    System.out.print(zombie.getName() + " ");
                }
            }
        }
        System.out.println();
    }
     */
}