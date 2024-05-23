package tubes.oop.pvz;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import java.util.Random;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Map {
    private int width = 11;
    private int height = 6;
    private final Tile[][] tiles;
    private Random random;
    private static int totalZombie = 0;

    public Map(int width, int height) {
        this.tiles = new Tile[height][width];
        this.random = new Random();
        
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 11; x++) {
                if (y==2 || y==3) {
                    tiles[y][x] = new Tile(x, y, "WATER");
                } else {
                    tiles[y][x] = new Tile(x, y, "GRASS");
                }
            }
        }
        // totalZombie = 0;
    }

    public void gameStart(){
        Time.start();
        removeZombieMap();
        // spawnRandomZombie();
        startSpawnZombie();
    }

    public Tile getTile(int x, int y) throws IndexOutOfBoundsException {
        if (isValidCoordinate(x, y)) {
            return tiles[y][x];
        } else {
            throw new IndexOutOfBoundsException("Invalid coordinates!");
        }
    }

    public void setTotalZombie(int x){
        totalZombie = x;
    }

    public void placePlant(Plant plant, int x, int y) {
        boolean notYet = true;
        if (isValidCoordinate(x, y)) {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    if (!getTile(j, i).isEmpty()) {
                        Plant thereIsPlant = getTile(j, i).getPlant();
                        if (thereIsPlant.getName().equals(plant.getName())) {
                            notYet = false;
                            break;
                        }
                    }
                }
                if (!notYet) {
                    break;
                }
            }            
            if (notYet) {
                getTile(x, y).setPlant(plant, this);
                plant.setLastPlantTime(System.currentTimeMillis()); 
                System.out.println("Plant " + plant.getName() + " (" + x + ", " + y + ")");
            } 
            else if(!notYet){
                if (isStillCooldown(plant)){
                    System.out.println("Plant is still in cooldown");
                }
                else if (!isStillCooldown(plant)) {
                    try {
                        getTile(x, y).setPlant(plant, this);
                        plant.setLastPlantTime(System.currentTimeMillis());
                        System.out.println("Plant " + plant.getName() + " (" + x + ", " + y + ")");
                    } catch (IllegalStateException e) {
                        System.out.println("Tile already has a plant");
                    }
                } 
            }
        } else {
            System.out.println("Invalid coordinates!");
        }
    }
    
    public boolean isStillCooldown(Plant plant) {
        long currentTime = System.currentTimeMillis();
        long lastPlantTime = plant.getLastPlantTime();
        long cooldown = plant.getCooldown();
        return (currentTime - lastPlantTime) < cooldown;
    }    

    public int getTotalZombie () {
        return totalZombie;
    }

    public void placeZombie(Zombie zombie, int x, int y) throws IllegalStateException, IndexOutOfBoundsException {
        if (isValidCoordinate(x, y)) {
            getTile(x, y).setZombie(zombie);
            zombie.startMoving();
            zombie.getDamaged();
        } else {
            throw new IndexOutOfBoundsException("Invalid coordinates!");
        }
    }
    
    public void spawnRandomZombie(){
        if((Time.getCurrentTime()>= 20) && (Time.getCurrentTime() <= 160)){

            if (totalZombie <= 10) {
                for (int y= 0; y<6; y++) {
                    double spawnPorbability = random.nextDouble();
                    if ((spawnPorbability<=0.3) && (totalZombie < 10)){
                        int probability = (int) (Math.random() * 10); 
                        Zombie zombie;

                        System.out.println(totalZombie);

                        if (probability == 1) {
                            zombie = new NormalZombie(10,y, this);
                        } 
                        else if (probability == 2) {
                            zombie = new BucketheadZombie(10,y, this);
                        } 
                        else if (probability == 3) {
                            zombie = new ConeheadZombie(10,y, this);
                        } 
                        else if (probability == 4) {
                            zombie = new DolphinRiderZombie(10,y, this);
                        } 
                        else if (probability == 5) {
                            zombie = new DuckyTubeZombie(10,y, this);
                        } 
                        else if (probability == 6) {
                            zombie = new FootballZombie(10,y, this);
                        } 
                        else if (probability == 7) {
                            zombie = new GiantZombie(10,y, this);
                        } 
                        else if (probability == 8) {
                            zombie = new JesterZombie(10,y,  this);
                        } 
                        else if (probability == 9) {
                            zombie = new PoleVaultingZombie(10,y, this);
                        } 
                        else {
                            zombie = new ShieldZombie(10,y, this);
                        }

                        if ((zombie.getIsAquatic()==true && getTile(10,y).getTileType() == "WATER") || (zombie.getIsAquatic()==false && getTile(10,y).getTileType() == "GRASS")) {
                            placeZombie(zombie, 10, y);
                            System.out.println(zombie.getName() + "(" + y + ") are starting to attack your fields!");
                            totalZombie += 1;
                        }
                    }
                }
            }
            else{
                return;
            }
        }

    }
        // if ((currentTime >= 20) && (currentTime <= 160) && (totalZombie < 10)) {
        //     for (int y = 0; y < height; y++) {
        //         if (random.nextDouble() <= 0.3 && totalZombie < 10) {
        //             double probability = random.nextDouble();
        //             Zombie zombie;
    
        //             if (probability < 0.1) {
        //                 zombie = new NormalZombie(8, y, this);
        //             } else if (probability < 0.2) {
        //                 zombie = new BucketheadZombie(8, y, this);
        //             } else if (probability < 0.3) {
        //                 zombie = new ConeheadZombie(8, y, this);
        //             } else if (probability < 0.4) {
        //                 zombie = new DolphinRiderZombie(8, y, this);
        //             } else if (probability < 0.5) {
        //                 zombie = new DuckyTubeZombie(8, y, this);
        //             } else if (probability < 0.6) {
        //                 zombie = new FootballZombie(8, y, this);
        //             } else if (probability < 0.7) {
        //                 zombie = new GiantZombie(8, y, this);
        //             } else if (probability < 0.8) {
        //                 zombie = new JesterZombie(8, y, this);
        //             } else if (probability < 0.9) {
        //                 zombie = new PoleVaultingZombie(8, y, this);
        //             } else {
        //                 zombie = new ShieldZombie(8, y, this);
        //             }
    
        //             System.out.println(zombie.getName() + "(" + y + ") are starting to attack your fields!");
    
        //             if ((zombie.getIsAquatic() && getTile(8, y).getTileType().equals("WATER")) ||
        //                     (!zombie.getIsAquatic() && getTile(8, y).getTileType().equals("GRASS"))) {
        //                 placeZombie(zombie, 8, y);
        //                 if (totalZombie >= 10) {
        //                     break; 
        //                 }
        //             }
        //         }
        //     }
        // }
    // }    
        

    public void startSpawnZombie () {
        Timer moveTimer = new Timer();
        moveTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                spawnRandomZombie();
            }
        }, 0, 3000);
    }

    // public void attackZombieInRange (){
    //     for (int i = tiles.length - 1; i > 0; i--) {
    //         for (int j = 0; j < tiles[0].length; j++) {
    //             if (!getTile(j, i).isEmpty()) {
    //                 Tile tile = tiles[i][j];
    //                 Plant plant = tile.getPlant();
                    
    //                 getZombieInRange(plant);
    //             }
    //         }
    //     }
    // }

    // public void removeZombie(Zombie zombie) {
    //     Tile tile = getTile(zombie.getX(), zombie.getY());
    //     tile.removeZombie(zombie);
    //     totalZombie--;
    // }    
    
    public void removeZombieMap() {

        Timer removeTimer = new Timer();
        removeTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                for (int i = 5; i >= 0; i--) {
                    for (int j = 1; j < 11; j++) {
                        if (!getTile(j, i).noZombie()) {
                            for (Zombie zombie : getTile(j, i).getZombie()) {
                                if (zombie.getHp() <= 0) {
                                    getTile(j, i).removeZombie(zombie);
                                    setTotalZombie(totalZombie-1);
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 1);
    }
    
    // public void getZombieInRange(Plant plant) {
    //     int plantRange = plant.getRange();
    //     int plantX = plant.getX();
    //     int plantY = plant.getY();


        
    //     if (plantRange == -1) {
    //         int zombieterdekat;
    //         int tanda =0 ;
    //         for (int j = tiles.length; j >= plantX; j--) {
    //             if (getTile(j, plantY).getZombie() != null) {
    //                 if (j<tanda) {
    //                     tanda = j;
    //                 }
    //             }
    //         }

    //         zombieterdekat = tanda;
    //         for (Zombie zombie : getTile(zombieterdekat, plantY).getZombie()) {
    //             if (plant.getName()=="Snow Pea") {
    //                 if (zombie.getName()=="Jester Zombie") {
    //                     zombie.takeDamage(plant);
    //                 } else {
    //                     zombie.applySnowPeaEffect();
    //                     zombie.takeDamage(plant);
    //                 }
    //             } else {
    //                 zombie.takeDamage(plant);
    //             }
    //         }
            
    //     } else if (plantRange == 1) {
    //         if (tiles[plantY][plantX].getZombie() != null) {
    //             for (Zombie zombie : getTile(plantX, plantY).getZombie()) {
    //                 zombie.takeDamage(plant);
    //             }

    //         } else if (tiles[plantY][plantX + 1].getZombie() != null) {
    //             for (Zombie zombie : getTile(plantX +1, plantY).getZombie()) {
    //                 zombie.takeDamage(plant);
    //             }
    //         }
    //     }
        
    // }

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
            System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
            for (int x = 0; x < width; x++) {
                //System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
                System.out.print("|" + tiles[y][x].getDisplayChar());
                if(x == 10){
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
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