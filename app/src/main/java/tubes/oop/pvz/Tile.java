package tubes.oop.pvz;

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

            try {
                plant.setX(this.getX());
                plant.setY(this.getY());
            } catch (InvalidPositionException e) {
                throw new IllegalStateException("Invalid coordinates!");
            }
        } else {
            throw new IllegalStateException("Tile already occupied!");
        }
    }

    public void setZombie(Zombie zombie) {
        zombieList.add(zombie);
        for (Zombie zombiese : zombieList) {
            zombie.currentTile = this;

        }
        // try {
        //     zombie.setX(this.getX());
        //     zombie.setY(this.getY());

        //     zombie.currentTile = this;
        // } catch (InvalidPositionException e) {
        //     throw new IllegalStateException("Invalid coordinates!");
        // }
    }

    public boolean isEmpty() {
        return getPlant()==null;
    }

    public boolean noZombie() {
        return getZombie().size() == 0;
    }

    public void removePlant() {
        this.plant = null;
    }

    public void removeZombie(Zombie zombie) {
        zombieList.remove(zombie);
    }

    public String getDisplayChar() {
        StringBuilder output = new StringBuilder();

        if (plant != null) {
            output.append('P');
        }
    
        for (Zombie zombie : zombieList) {
            output.append('Z');
        }
    
        if (output.length() == 0) {
            if (tileType.equals("WATER") && x>0 && x<10) {
                output.append('W');
            } else if (tileType.equals("GRASS") && x>0 && x<10){
                output.append('G');
            }
        }

        int space = 10 - output.length();
        if (output.length() < 10){
            for(int i = 0; i < (space); i++){
                output.append(' ');
            }
        }
    
        return output.toString();
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

    // public void move(Map map) {
    //     for (Zombie zombie : new ArrayList<>(zombieList)) {
    //         int newCol = zombie.getX() - 1;
    //         if (newCol < 0) {
    //             System.out.println("Game Over! Zombie reached the end");
    //             System.exit(0);
    //         }

    //         Tile nextTile = map.getTile(zombie.getY(), newCol);
    //         if (nextTile.isEmpty()) {
    //             removeZombie(zombie);
    //             if(nextTile.getZombie() == null){
    //                 System.out.println("Game selesai");//gangerti gajelasz
    //             }
    //             else{
    //                 nextTile.setZombie(zombie);//belum nanganin kalo next tilenya tuh null
    //                 try {
    //                     zombie.setX(newCol);
    //                 } catch (InvalidPositionException e) {
    //                     System.out.println(e.getClass().getSimpleName() + "! " + e.getMessage());
    //                 }
    //                 zombie.setJarak(zombie.getJarak() + 1);
    //             }
    //         } else {
    //             // Attack the plant or wait for the tile to be cleared
    //         }

    //         try {
    //             Thread.sleep(5000); // Delay for MOVE_DELAY seconds
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     // BENERIN!!
    //     // BUAT IMPLEMENTASI TAKE DAMAGE DARI PLANT SAMPE ZOOM
    // }
    
}