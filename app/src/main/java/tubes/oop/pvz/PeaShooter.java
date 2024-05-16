package tubes.oop.pvz;

public class PeaShooter extends Shooter{

    public PeaShooter(int x, int y){
        super("Pea Shooter", 100, 25, 4000, 100, -1, 10, x, y);
    }

    @Override
    public void shoot() {
        System.out.println("Pea Shooter is Shooting!");
    }
}