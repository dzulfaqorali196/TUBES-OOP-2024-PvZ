package tubes.oop.pvz;

// import java.util.Random;
import java.util.List;
// import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Zombie extends PlantandZombie {
    private boolean isAquatic;
    protected int jarak;
    protected long movement_speed;
    public boolean isSlow;
    Tile currentTile;
    Timer moveTimer;
    Timer attackTimer;
    Timer snowPeaTimer;
    Timer getdamaged;
    boolean terdepan = false;



    boolean isAttacking;
    Map map;


    public Zombie(String name, int hp, int attack_damage, int attack_speed, long movement_speed, boolean isAquatic, int x, int y, int jarak, Map map) {
        super(name, hp, attack_damage, attack_speed, x, y);
        this.movement_speed = movement_speed;
        this.isAquatic = isAquatic;
        isSlow = false; //0 = gak slow, 1= slow
        this.moveTimer = new Timer();
        this.attackTimer = new Timer();
        this.getdamaged = new Timer();
        this.isAttacking = false;
        this.map = map;
        this.snowPeaTimer = new Timer();


        jarak = 0;

    }

    public int getJarak(){
        return jarak;
    }

    public void setJarak(int jarak){
        this.jarak = jarak;
    }

    public boolean getIsAquatic(){
        return this.isAquatic;
    }
    
    public void takeDamage(int damage) {
        this.hp -= damage;
        // if (isDead()) {
        //     map.removeZombie(this);
        // }
    }
    
    
    // public void eatPlant(List<Plant> allPlants) {
    //     boolean foundPlant = false;
    //     synchronized (allPlants) {
    //         Iterator<Plant> i = allPlants.iterator();
    //         while (i.hasNext()) {
    //             Plant p = i.next();
    //             if (p.getX() == getX() && Math.abs(p.getY() - getY()) <= 25) {
    //                 foundPlant = true;
    //                 p.takeDamage(getAttackDamage());
    //                 if (p.getHp() <= 0) {
    //                     i.remove(); // Hilangkan tanaman dari list
    //                     System.out.println("Plant eaten!");
    //                 }
    //             }
    //         }
    //     }
    //     isEating = foundPlant;
    // }

    //menentukan apakah zombie sudah di akhir tile paling kiri 
    public boolean isAtGoal() {
        return x <= 0;  // Hanya periksa posisi x untuk menentukan apakah zombie telah mencapai tujuan
    }

    public void setIsSlow(boolean isSlow){
        this.isSlow = isSlow;
    }
      
    // menentukan apakah zombie terkena tembakan snowpea
    public boolean getIsSlow(){
        return isSlow;
    }

    public void applySnowPeaEffect() {
        if (isSlow) {
            snowPeaTimer.cancel();
        }
        isSlow = true;
        this.attack_speed += attack_speed/2;
        this.movement_speed += movement_speed/2;

        snowPeaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeSnowPeaEffect();
            }
        }, 3000); // Efek berlangsung selama 3 detik
    }

    public void removeSnowPeaEffect() {
        attack_speed -= attack_speed/3;
        movement_speed -= movement_speed/3;
        isSlow = false;
    }

    public void attack(Plant plant) {
        plant.takeDamage(this);
    }

    public void startAttacking(Plant plant) {
        isAttacking = true;
        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (plant.getHp() <= 0) {
                    map.getNextTile(currentTile).removePlant();
                    isAttacking = false;
                    if (!isDead()) {
                        // Mulai ulang moveTimer setelah 5 detik
                        moveTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startMoving();
                            }
                        }, movement_speed); // 10 detik setelah penyerangan selesai
                    }
                } else {
                    attack(plant);
                }
            }
        }, 0, attack_speed);
    }

    public void getDamaged() {
            getdamaged.schedule(new TimerTask() {
                @Override
                public void run() {
                    for (int j= getX()-1; j >=0; j--) {
                        if (map.getTile(j,getY()).noZombie()) {
                            terdepan = true;
                        } else {
                            terdepan = false;
                            break;
                        }
                    }

                    for (int i = getX(); i > 0; i--) {
                        if(!map.getTile(i, getY()).isEmpty()){
                            if (map.getTile(i, getY()).getTileType().equals("WATER") && map.getTile(i, getY()).getPlant() instanceof Lilypad) {
                                Lilypad lilypad = (Lilypad) map.getTile(i, getY()).getPlant();
                                if (!lilypad.isEmpty()) {
                                    if (lilypad.getPlant().getRange()==-1) {
                                        if (terdepan) {
                                            if (lilypad.getPlant().equals("Snow Pea")) {
                                                takeDamage(lilypad.getPlant().getAttackDamage());
                                                applySnowPeaEffect();
                                            } else {
                                                takeDamage(lilypad.getPlant().getAttackDamage());
                                            }
                                        } else {
                                            return;
                                        }
                                    }
                                    
                                } else {
                                    return;
                                }
                                    
                            } else if (map.getTile(i, getY()).getTileType().equals("GRASS")) {
                                if (map.getTile(i, getY()).getPlant().getRange()==-1) {
                                    if (terdepan) {
                                        if (map.getTile(i, getY()).getPlant().getName()=="Snow Pea") {
                                            takeDamage(map.getTile(i, getY()).getPlant().getAttackDamage());
                                            applySnowPeaEffect();
                                        } else {
                                            takeDamage(map.getTile(i, getY()).getPlant().getAttackDamage());
                                        }
                                    } else {
                                        return;
                                    }     
                                }
                            }
                        } 
                    }
                }
            }, 0, 4000);
    }

    public void move(Tile fromTile, Tile toTile) {
        // Zombie zombies = currentTile.getZombie().get(0);
        fromTile.removeZombie(this);
        toTile.setZombie(this);
        this.currentTile = toTile;

        System.out.println("Zombie moved to tile (" + toTile.getX() + ", " + toTile.getY() + ") darah : " + this.hp + " slow :" + this.isSlow);
        try {
            this.setX(this.getX()-1);
        } catch (InvalidPositionException e) {
            // Handle the exception here
        }
    }

    public abstract void startMoving();
}