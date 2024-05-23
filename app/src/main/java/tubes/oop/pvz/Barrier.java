package tubes.oop.pvz;
public abstract class Barrier extends Plant{

    public Barrier(String name, int hp, int attack_damage, int attack_speed, int cost, int range, long cooldown, int x, int y) {
        super(name, hp, attack_damage, attack_speed, cost, range, cooldown, x, y);
    }

    public abstract void armor();
}