package tubes.oop.pvz;

public abstract class Aquatic extends Plant{

    public Aquatic(String name, int hp, int attack_damage, int attack_speed, int cost, int range, long cooldown, boolean isAquatic, int x, int y) {
        super(name, hp, attack_damage, attack_speed, cost, range, cooldown, isAquatic, x, y);
    }

    public abstract void water();
}