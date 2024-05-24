package tubes.oop.pvz;

import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Time {

    private static final int gameDuration = 200;
    private static final int intervalZombie = 3;
    private static final int zombieProbability = 30;

    private static int currentTime = 0;
    private static boolean isMorning = true;
    private static Timer startTime;
    // ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public static int getCurrentTime() {
        return currentTime;
    }

    public static void resetCurrentTime () {
        currentTime = 0;
    }

    public static boolean isMorning() {
        return isMorning;
    }

    public static void start() {
        startTime = new Timer();
        startTime.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                currentTime++;
                if (99 < currentTime && currentTime <= gameDuration) {
                    isMorning = !isMorning;
                }
            }
            
        }, 0, 1000);

        // ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Runnable startTime = new Runnable() {
        //     //private int time = 0;

        //     @Override
        //     public void run() {
        //         currentTime++;
        //         if (99 < currentTime && currentTime <= gameDuration) {
        //             isMorning = !isMorning;
        //         }
    
                
        //     }
        // };
        // scheduler.scheduleAtFixedRate(startTime, 0, 1, TimeUnit.SECONDS);


        // scheduler.scheduleAtFixedRate(() -> {
        //     currentTime++;

        //     if (99 < currentTime && currentTime <= gameDuration) {
        //         isMorning = !isMorning;
        //     }

        //     if (currentTime % intervalZombie == 0) {
        //         if (Math.random() * 100 < zombieProbability) {
        //             // BENERIN!! tinggal manggil random dari map
        //         }
        //     }

        // }, 0, 1, TimeUnit.SECONDS);
    }

    public static void stop() {
        if (startTime != null) {
            startTime.cancel();
            startTime.purge();
        }
        System.out.println("Selamat, kamu memenangkan permainan!");
    }

    public void scheduleAtFixedRate(TimerTask timerTask, int i, int j) {
        throw new UnsupportedOperationException("Unimplemented method 'scheduleAtFixedRate'");
    }
}
