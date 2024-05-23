package tubes.oop.pvz;

public class TangleKelp extends Aquatic{

    public TangleKelp(int x, int y) {
        super("Tangle Kelp", 100, 0, 0, 25, 0, 10000, x, y);
    }

    @Override
    public void water() {
        System.out.println("Tangle Kelp for Aquatic Only!");
    }
}