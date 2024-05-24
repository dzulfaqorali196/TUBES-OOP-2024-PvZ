package tubes.oop.pvz;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class Sunflower extends SunProduce{
    private Player player;

    public Sunflower(int x, int y, Player player) {
        super("Sunflower", 100, 0, 0, 50, 0, 10000, false, x, y);
        this.player = player;
    }

    @Override
    public void sunProduct() {
        System.out.println("Sunflower Producing Sun!");
    }

    
    public void produceSun() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable sunTask = new Runnable() {

            @Override
            public void run() {
                player.increaseSun(25);
                System.out.println("Sunflower producing 25 sun");
                System.out.println("Sun: " + player.getSunScore());
            }
        };
        scheduler.scheduleAtFixedRate(sunTask, 3, 3, TimeUnit.SECONDS);
    }

        
    
}