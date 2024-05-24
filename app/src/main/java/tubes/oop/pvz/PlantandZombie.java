package tubes.oop.pvz;

public abstract class PlantandZombie{
    String name;
    int hp;
    int attack_damage;
    int attack_speed;
    int x;
    int y;
    

    public PlantandZombie(String name, int hp, int attack_damage, int attack_speed, int x, int y){
        this.name = name;
        this.hp = hp;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.x = x;
        this.y = y;
    }

    public String getName(){
        return name;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    public int getAttackDamage(){
        return attack_damage;
    }

    public int getAttackSpeed(){
        return attack_speed;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int increaseHP(int minusHp){
        return hp -= minusHp;
    }

    public int decreaseHP(int plusHp){
        return hp += plusHp;
    }

    public void setHp(int newHP){  
        if (newHP >= 0)  
            hp = newHP; 
    }
    
    public boolean isDead(){
        if(hp <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void setX(int x) throws InvalidPositionException{
        if(0 <= x && x < 11){
            this.x = x;
        }
        else{
            throw new InvalidPositionException("Posisi X zombie tidak valid! Batas map adalah 1 sampai 9.");
        }
    }

    public void setY(int y) throws InvalidPositionException{
        if(0 <= y && y < 6){
            this.y = y;
        }
        else{
            throw new InvalidPositionException("Posisi Y zombie tidak valid! Batas map adalah 1 sampai 6.");
        }
    }
}

class InvalidPositionException extends java.lang.Exception {
    private String message;

    public InvalidPositionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
