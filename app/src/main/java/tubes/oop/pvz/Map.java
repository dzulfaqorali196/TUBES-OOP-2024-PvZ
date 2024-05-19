package tubes.oop.pvz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.Timer;
import java.util.TimerTask;


public class Map {
    private final int width = 9;
    private final int height = 6;
    private final Tile[][] tiles;
    private Random random;

    public Map(int width, int height) {
        this.tiles = new Tile[height][width];
        this.random = new Random();
        
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 9; x++) {
                if (y==2 || y==3) {
                    tiles[y][x] = new Tile(x, y, "WATER");
                } else {
                    tiles[y][x] = new Tile(x, y, "GRASS");
                }
            }
        }
        //startSpawnZombie();
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
        if (isValidCoordinate(x, y)) {
            getTile(x, y).setZombie(zombie);
        } else {
            throw new IndexOutOfBoundsException("Invalid coordinates!");
        }
    }

    public void spawnRandomZombie(int currentTime){
        int totalzombie = 0;

        if((currentTime >= 20) && (currentTime <= 160)){

            if (totalzombie <= 10) {
                for (int y= 0; y<6; y++) {
                    if (random.nextDouble()<=0.3) {
                        double probability = random.nextDouble();
                        Zombie zombie;

                        if (probability < 0.1) {
                            zombie = new NormalZombie(8,y, this);
                        } 
                        else if (probability < 0.2) {
                            zombie = new BucketheadZombie(8,y, this);
                        } 
                        else if (probability < 0.3) {
                            zombie = new ConeheadZombie(8,y, this);
                        } 
                        else if (probability < 0.4) {
                            zombie = new DolphinRiderZombie(8,y, this);
                        } 
                        else if (probability < 0.5) {
                            zombie = new DuckyTubeZombie(8,y, this);
                        } 
                        else if (probability < 0.6) {
                            zombie = new FootballZombie(8,y, this);
                        } 
                        else if (probability < 0.7) {
                            zombie = new GiantZombie(8,y, this);
                        } 
                        else if (probability < 0.8) {
                            zombie = new JesterZombie(8,y,  this);
                        } 
                        else if (probability < 0.9) {
                            zombie = new PoleVaultingZombie(8,y, this);
                        } 
                        else {
                            zombie = new ShieldZombie(8,y, this);
                        }
                        System.out.println(zombie.getName() + "(" + y + ") are starting to attack your fields!");

                        if ((zombie.getIsAquatic()==true && getTile(8,y).getTileType() == "WATER") || (zombie.getIsAquatic()==false && getTile(8,y).getTileType() == "GRASS")) {
                            placeZombie(zombie, 8, y);
                        
                        }
                        totalzombie += 1;
                    }
                }
            }
            else{
                return;
            }
        }

    }

    // public void startSpawnZombie () {
    //     Timer timer = new Timer();
    //     TimerTask task = new TimerTask() {
    //         @Override
    //         public void run() {
    //             spawnRandomZombie(timer);
    //         }
    //     };
    //     timer.schedule(task, 0, 1000);

    // }

    public void attackZombieInRange (){
        for (int i = tiles.length - 1; i > 0; i--) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (!getTile(j, i).isEmpty()) {
                    Tile tile = tiles[i][j];
                    Plant plant = tile.getPlant();
                    
                    getZombieInRange(plant);
                }
            }
        }
    }

    public void removeZombieMap() {
        for (int i = tiles.length - 1; i > 0; i--) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (!getTile(j, i).noZombie()) {
                    Tile tile = tiles[i][j];
                    for (Zombie zombie : tile.getZombie()) {
                        if (zombie.getHp() <= 0) {
                            tile.removeZombie(zombie);
                        }
                    }
                }
            }
        }
    }

    public void getZombieInRange(Plant plant) {
        int plantRange = plant.getRange();
        int plantX = plant.getX();
        int plantY = plant.getY();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        
        if (plantRange == -1) {
            for (int j = plantX; j < tiles.length; j++) {
                if (getTile(j, plantY).getZombie() != null) {
                    List<Zombie> zombieList = getTile(j, plantY).getZombie();
                    Runnable task = () -> {
                        for (Zombie zombie : zombieList) {
                            if (plant.getName()=="Snow Pea") {
                                if (zombie.getName()=="Jester Zombie") {
                                    zombie.takeDamage(plant);
                                } else {
                                    zombie.applySnowPeaEffect();
                                    zombie.takeDamage(plant);
                                }
                            } else {
                                zombie.takeDamage(plant);
                            }
                        }
                    };
                    scheduler.scheduleAtFixedRate(task, 0, plant.getAttackSpeed(), TimeUnit.SECONDS);
                }
            }
        } else if (plantRange == 1) {
            if (tiles[plantY][plantX].getZombie() != null) {
                List<Zombie> zombieList = getTile(plantX, plantY).getZombie();
                for (Zombie zombie : zombieList) {
                    zombie.takeDamage(plant);
                }

            } else if (tiles[plantY][plantX + 1].getZombie() != null) {
                List<Zombie> zombieList = getTile(plantX +1, plantY).getZombie();
                for (Zombie zombie : zombieList) {
                    zombie.takeDamage(plant);
                }
            }
        }
        
    }

    private boolean isValidCoordinate(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }

    public Tile getNextTile(Tile currentTile) {
        int x = currentTile.getX();
        int y = currentTile.getY();

        if (x > 0) {
            return tiles[y][x-1];
        } else {
            return null;
        }
    }

    // public void zombieAct () {
    //     ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    //     for (int i = 0; i < 6; i++) {
    //         for (int j = 0; j < 9; j++) {
    //             if (!getTile(j, i).noZombie()) {
    //                 int zi = i;
    //                 int zj = j;
    //                 Tile tile = tiles[zi][zj];
    //                 Tile nexttile = tiles[zi][zj - 1];
    //                 List<Zombie> zombieList = tile.getZombie();
    //                 while (nexttile.getY()>=0) {
    //                     if (tile.isEmpty() && nexttile.isEmpty()) {
    //                         for (Zombie zombie : zombieList) {
    //                             moveZombie(zombie, zi, zj);
    //                         }
    //                     } else if (!tile.isEmpty()) {
    //                         for (Zombie zombie : zombieList) {
    //                             Runnable task = () -> {
    //                                 tile.getPlant().takeDamage(zombie);
    //                             };
    //                             scheduler.scheduleAtFixedRate(task, 0, zombie.getAttackSpeed(), TimeUnit.SECONDS);
    //                         }
    //                     } else if (!nexttile.isEmpty()) {
    //                         for (Zombie zombie : zombieList) {
    //                             Runnable task = () -> {
    //                                 nexttile.getPlant().takeDamage(zombie);
    //                             };
    //                             scheduler.scheduleAtFixedRate(task, 0, zombie.getAttackSpeed(), TimeUnit.SECONDS);
    //                         }
    //                     }
    //                 }   
    //             }
    //         }
    //     }
    // }

    // public void moveZombie(Zombie zombie, int zi, int zj) {
    //     ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    //     if (zombie.getIsSlow() == 0) {
    //         Runnable task1 = () -> {
    //             tiles[zi][zj].removeZombie(zombie);
    //             placeZombie(zombie, zi - 1, zj);
    //         };
    
    //         scheduler.scheduleAtFixedRate(task1, 0, 5, TimeUnit.SECONDS);
    //     } else {
    //         Runnable task2 = () -> {
    //             tiles[zi][zj].removeZombie(zombie);
    //             placeZombie(zombie, zi - 1, zj);
    //         };
    
    //         scheduler.scheduleAtFixedRate(task2, 0, 5/2, TimeUnit.SECONDS);
    //     }
    // }

    public void printMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
                System.out.print("|" + tiles[y][x].getDisplayChar() + "|");
            }
            //System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
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
