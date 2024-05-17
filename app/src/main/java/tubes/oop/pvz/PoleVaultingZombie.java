package tubes.oop.pvz;

public class PoleVaultingZombie extends Zombie implements SpecialMove {

    private boolean hasJumped;

    public PoleVaultingZombie(int x, int y) {
        super("Pole Vaulting Zombie", 175, 100, 1, false, x, y, 0);
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
