package tubes.oop.pvz;

public class RepeaterPea extends Shooter{

    public RepeaterPea(int x, int y){
        super("Repeater Pea", 100, 50, 4000, 150, -1, 10, x, y);
    }

    @Override
    public void shoot() {
        System.out.println("Repeater Pea is Shooting!");
    }
}