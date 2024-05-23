package tubes.oop.pvz;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Time {

    private static final int gameDuration = 200;
    private static final int intervalZombie = 3;
    private static final int zombieProbability = 30;

    private static int currentTime = 0;
    private static boolean isMorning = true;
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static int getCurrentTime() {
        return currentTime;
    }

    public static boolean isMorning() {
        return isMorning;
    }

    public static void start() {
        scheduler.scheduleAtFixedRate(() -> {
            currentTime++;

            if (99 < currentTime && currentTime <= gameDuration) {
                isMorning = !isMorning;
            }

            if (currentTime % intervalZombie == 0) {
                if (Math.random() * 100 < zombieProbability) {
                    // BENERIN!! tinggal manggil random dari map
                }
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void endGame() {
        scheduler.shutdown();
        System.out.println("Selamat, kamu memenangkan permainan!");
    }

    public void scheduleAtFixedRate(TimerTask timerTask, int i, int j) {
        throw new UnsupportedOperationException("Unimplemented method 'scheduleAtFixedRate'");
    }
}
