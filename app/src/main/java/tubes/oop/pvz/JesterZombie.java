package tubes.oop.pvz;

public class JesterZombie extends Zombie {
    
    public JesterZombie(int x, int y) {
        super("Jester Zombie", 200, 100, 1  , false, x, y, 0);
    }

    @Override
    public boolean isSlow() {
        return false;  // Jester Zombie kebal terhadap efek slow
    }
}
