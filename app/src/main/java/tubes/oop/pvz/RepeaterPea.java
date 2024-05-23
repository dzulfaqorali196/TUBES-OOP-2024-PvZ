package tubes.oop.pvz;

import java.util.TimerTask;

public class RepeaterPea extends Shooter{

    public RepeaterPea(int x, int y) {
        super("Repeater Pea", 100, 50, 4, 150, -1, 10000, false, x, y);
    }

    @Override
    public void shoot() {
        System.out.println("Repeater Pea is Shooting!");
    }
}