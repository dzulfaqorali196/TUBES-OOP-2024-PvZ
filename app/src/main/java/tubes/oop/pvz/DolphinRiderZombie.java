package tubes.oop.pvz;

public class DolphinRiderZombie extends Zombie implements SpecialMove {

    private boolean hasJumped;

    public DolphinRiderZombie(int x, int y) {
        super("Dolphin Rider Zombie", 175, 100, 1, true, x, y, 0);
        this.hasJumped = false;
    }

    @Override
    public void specialMove(Tile currentTile, Tile nextTile) {
        try {
            if (!hasJumped) {
                if (nextTile.getPlant() != null) {
                    nextTile.removePlant();
                }
                this.hasJumped = true;
                // Logika untuk melompat ke tile berikutnya
                this.setX(this.getX() - 2); // Melompat dua tile ke depan
            } else {
                // Gerakan normal jika sudah melompat
                this.setX(this.getX() - 1);
            }
        } catch (InvalidPositionException e) {
            System.err.println("Invalid position: " + e.getMessage());
        }
    }
}
