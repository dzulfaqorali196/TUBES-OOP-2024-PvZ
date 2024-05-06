// Class GiantZombie
public class GiantZombie extends Zombie implements SpecialMovement {
    public GiantZombie() {
        super("Giant Zombie", 1000, 100, 0.5, false, 5);
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
        // Giants cannot leap over plants
        return false;
    }
    
    @Override
    public void getLeapRange() {
        // Implementation to get leap range (not applicable for giants)
    }
}
