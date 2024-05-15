public class TallWallnut extends Barrier{

    public TallWallnut(int x, int y){
        super("Tall Wall-nut", 1000, 0, 0, 75, 0, 20, x, y);
    }

    @Override
    public void armor() {
        System.out.println("Tall Wall-nut go strong!");
    }
}