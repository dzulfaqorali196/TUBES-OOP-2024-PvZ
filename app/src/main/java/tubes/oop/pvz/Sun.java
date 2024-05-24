package tubes.oop.pvz;

import java.util.concurrent.ScheduledExecutorService;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Sun extends Player {
    private Timer timer;
    private ScheduledExecutorService scheduler;

    public Sun() {
        //super(50);
        // startSunGeneration();
    }

    public void stopSunGeneration() {
        timer.cancel();
    }
}