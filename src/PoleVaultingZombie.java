// Class PoleVaultingZombie
public class PoleVaultingZombie extends Zombie implements SpecialMovement {
    private boolean hasJumped;
    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, 5);
        this.hasJumped = false;
    }
    
    @Override
    public void eatPlant() {
        // Implementation
    }
    
    @Override
    public void move() {
        // Implementation
    }
    
    @Override
    public boolean isSlow() {
        // Implementation
    }
    
    @Override
    public boolean isAtGoal() {
        // Implementation
    }
    
    // Implement interface methods
    @Override
    public boolean leapOverPlant() {
        if (!hasJumped) {
            // Implementation to leap over plant
            hasJumped = true;
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void getLeapRange() {
        // Implementation to get leap range
    }
}
