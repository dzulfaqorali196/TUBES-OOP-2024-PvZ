public abstract class Plant extends Organism{
    int range;
    int cooldown;
    int x;
    int y;
    Tile tile;
    int cost;

    public Plant(String name, int cost, int hp, int attack_damage, int attack_speed, int range, int cooldown){
        super(name, hp, attack_damage, attack_speed);
        this.range = range;
        this.cooldown = cooldown;
        this.tile = null;
        this.cost = cost;
    }

    public abstract void attack(Zombie target); //fix

    public void takeDamage(int damage){ //fix
        hp -= damage;
        if(hp <= 0){
            plantDie();
        }
    }

    public void plantDie(){ //fix
        if(isPlanted(x, y)){
            tile.setPlant(null);
            tile = null;
        }
    }
    
    public boolean isAttacked(int damage){
        return false;
    }

    public boolean isOrdinatValid(int x, int y){
        if((x <= 9 && x >= 1) && (y <= 6 && y >= 1)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isPlanted(int x, int y){
        return tile != null;
    }

    public String getPlantName(){
        return name;
    }

    public int getCostPlant(){
        return cost;
    }

    public int getHp(){
        return hp;
    }
    
    public int getAttackDamage(){
        return attack_damage;
    }

    public int getAttackSpeed(){
        return attack_speed;
    }

    public int getRange(){
        return range;
    }

    public int getCooldown(){
        return cooldown;
    }

    public Tile getTile(){
        return tile;
    }

    public boolean isStillCooldown(){
        return true; //lanjutin
    }
}