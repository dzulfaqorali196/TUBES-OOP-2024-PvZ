package tubes.oop.pvz;

public class TangleKelp extends Aquatic{

    public TangleKelp(int x, int y) {
        super("Tanglekelp", 100, 0, 0, 25, 0, 10000, true, x, y);
    }

    @Override
    public void water() {
        System.out.println("Tanglekelp for Aquatic Only!");
    }
}