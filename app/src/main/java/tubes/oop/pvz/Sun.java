package tubes.oop.pvz;

import java.util.concurrent.ScheduledExecutorService;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Sun extends Player {
    private Timer timer;
    private ScheduledExecutorService scheduler;

    public Sun() {
        super(50);
        // startSunGeneration();
    }

    // public void startSunGeneration() {
    //     Runnable sunTask = new Runnable() {
    //         @Override
    //         public void run() {
    //             int delay = 5 + (int)(Math.random() * ((10 - 5) + 1)); 
    //             scheduler.schedule(new Runnable() {
    //                 @Override
    //                 public void run() {
    //                     increaseSun(25); 
    //                     System.out.println("Sun: " + getSunScore());
    //                 }
    //             }, delay, TimeUnit.SECONDS);
    //         }
    //     };

    //     scheduler.scheduleAtFixedRate(sunTask, 0, 10, TimeUnit.SECONDS);
    // }

    public void stopSunGeneration() {
        timer.cancel();
    }
}