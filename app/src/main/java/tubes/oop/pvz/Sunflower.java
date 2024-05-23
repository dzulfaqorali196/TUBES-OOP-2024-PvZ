package tubes.oop.pvz;

public class Sunflower extends SunProduce{

    public Sunflower(int x, int y) {
        super("Sunflower", 100, 0, 0, 50, 0, 10000, false, x, y);
    }

    @Override
    public void sunProduct() {
        System.out.println("Sunflower Producing Sun!");
    }
}