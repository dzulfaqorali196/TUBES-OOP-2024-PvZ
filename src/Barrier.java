public abstract class Barrier extends Plant{

    public Barrier(String name, int hp, int attack_damage, int attack_speed, int cost, int range, int cooldown, int x, int y){
        super(name, hp, attack_damage, attack_speed, x, y, cost, range, cooldown);
    }

    public abstract void armor();
}