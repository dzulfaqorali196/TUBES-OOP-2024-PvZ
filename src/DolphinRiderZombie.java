// Class DolphinRiderZombie
public class DolphinRiderZombie extends Zombie implements SpecialMovement {
    private boolean hasJumped;
    
    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, true, 5);
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