package tubes.oop.pvz;

public class PeaShooter extends Shooter{

    public PeaShooter(int x, int y) {
        super("Pea Shooter", 100, 25, 4, 100, -1, 10000, false, x, y);
    }

    @Override
    public void shoot() {
        System.out.println("Pea Shooter is Shooting!");
    }
}