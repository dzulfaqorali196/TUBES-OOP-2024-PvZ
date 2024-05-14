import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public Void spawnRandomZombie(/*ArrayList<Integer> numberZombie, double probability*/){
        // BENERIN!!!
        Random randZombie = new Random();
        int pilihanzombie = randZombie.nextInt(numberZombie.size()) + 1;
        
        Random randInt = new Random();
        int row = randInt.nextInt(6);
        /* nunggu class zombie zombie lainnya
        if (pilihanzombie == 1) {
            NormalZombie zombie = new NormalZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 2) {
            ConeHeadZombie zombie = new ConeHeadZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 3) {
            BucketHeadZombie zombie = new BucketHeadZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 4) {
            PoleVaultingZombie zombie = new PoleVaultingZOmbie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 5) {
            DuckyTubeZombie zombie = new DuckyTubeZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 6) {
            DolphinRiderZombie zombie = new DolphinRiderZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 7) {
            FootballZombie zombie = new FootballZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 8) {
            ShieldZombie zombie = new ShieldZombie();
            placeZombie(zombie, row, 9);
        } else if (pilihanzombie == 9) {
            JesterZombie zombie = new JesterZombie();
            placeZombie(zombie, row, 9);
        } */


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
    
    public void getPlantsInRow(int row) {
        for (int x = 0; x < width; x++) {
            Plant plantinRow = tiles[row][x].getPlant();
            if (plantinRow != null) {
                plantinRow.getAttackDamage();
                plantinRow.getX();
            }

        }


    }

    public void getZombiesInRow(int row) {
        for (int x = 0; x < width; x++) {
            List<Zombie> zombies = tiles[row][x].getZombie();
            if (!zombies.isEmpty()) {
                for (Zombie zombie : zombies) {
                    zombie.getAttackDamage();  
                    zombie.getX();
                }
            }
        }
    }

 
/* masih mikirin implementasinya
    public void applyDamageToClosestZombie(List<Plant> plants, List<Zombie> zombies) {
        for (Plant plant : plants) {
            Zombie closestZombie = null;
            double minDistance = Double.MAX_VALUE;
    
            for (Zombie zombie : zombies) {
                double distance = Math.sqrt(Math.pow(zombie.getX() - plant.getX(), 2) + Math.pow(zombie.getY() - plant.getY(), 2));
    
                // Pilih zombie dengan koordinat x terbesar yang masih dalam jarak serang
                if ((zombie.getX() > plant.getX()) && (distance < minDistance || (distance == minDistance && zombie.getX() > (closestZombie != null ? closestZombie.getX() : Double.MIN_VALUE)))) {
                    minDistance = distance;
                    closestZombie = zombie;
                }
            }
    
            // Terapkan damage jika ada zombie yang cukup dekat
            if (closestZombie != null && minDistance <= plant.getRange()) {
                int damage = plant.getAttackDamage();
                closestZombie.takeDamage(damage); // Pastikan kelas Zombie memiliki metode takeDamage(int damage)
                System.out.println("Zombie at (" + closestZombie.getX() + ", " + closestZombie.getY() + ") took " + damage + " damage from plant at (" + plant.getX() + ", " + plant.getY() + ")");
            }
        }
    }

    
    public void applyDamageToZombiesInSameTile(List<Plant> plants, List<Zombie> zombies) {
        for (Plant plant : plants) {
            // Iterasi melalui semua zombie untuk menemukan yang berada dalam jangkauan serangan tanaman
            for (Zombie zombie : zombies) {
                double distance = Math.sqrt(Math.pow(zombie.getX() - plant.getX(), 2) + Math.pow(zombie.getY() - plant.getY(), 2));
    
                // Cek apakah zombie dalam jangkauan serangan
                if (distance <= plant.getRange()) {
                    int damage = plant.getAttackDamage();
                    zombie.takeDamage(damage); // Asumsikan Zombie memiliki metode takeDamage(int damage)
                    System.out.println("Zombie at (" + zombie.getX() + ", " + zombie.getY() + ") took " + damage + " damage from plant at (" + plant.getX() + ", " + plant.getY() + ")");
                }
            }
        }
    }
*/
    
    
    
}