package tubes.oop.pvz;

import java.util.Timer;
import java.util.TimerTask;

public class Sun extends Player {
    private Timer timer;

    public Sun() {
        super(25);
        this.timer = new Timer();
        startSunGeneration();
    }

    private void startSunGeneration() {
        TimerTask generateSun = new TimerTask() {
            @Override
            public void run() {
                increaseSun(25);
                System.out.println("Sun: " + getSunScore());
            }
        };
        timer.schedule(generateSun, 0, 3000);
    }

    public void stopSunGeneration() {
        timer.cancel();
    }
}