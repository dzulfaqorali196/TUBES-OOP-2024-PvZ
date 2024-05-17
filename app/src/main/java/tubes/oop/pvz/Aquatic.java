package tubes.oop.pvz;

public abstract class Aquatic extends Plant{

    public Aquatic(String name, int hp, int attack_damage, int attack_speed, int cost, int range, int cooldown, int x, int y){
        super(name, hp, attack_damage, attack_speed, cost, range, cooldown, x, y);
    }

    public abstract void water();
}