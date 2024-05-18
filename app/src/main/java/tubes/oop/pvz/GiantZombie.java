package tubes.oop.pvz;

public class GiantZombie extends Zombie {

    public GiantZombie(int x, int y) {
        super("Giant Zombie", 1000, 100, 1, false, x, y, 0);
    }

    public void move(Tile[][] map) throws InvalidPositionException {
        int x = this.getX();
        int y = this.getY();

        if (x > 0) {
            Tile currentTile = map[x][y];
            // Hapus tanaman di tile saat ini
            if (currentTile.getPlant() != null) {
                currentTile.removePlant();
            }
            // Pindahkan zombie ke tile berikutnya
            this.setX(x - 1);
        }
    }
}
