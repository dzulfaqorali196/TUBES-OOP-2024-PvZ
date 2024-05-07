import java.util.Timer;
import java.util.TimerTask;

public class Sun {

    private static Timer timer;
    private static int cycleDuration = 100; // durasi setiap sun cycle
    private static int currentCycle = 0; 
    private static boolean shouldStopSunScore = false; // indikasi stop
    private static boolean isAfternoon = false; // buat kondisi stop
    private static Thread sunThread; // thread untuk sun cycle
    private static long startTime; 

    public Sun() {
        // tidak butuh
    }

    public static void start() {
      if (sunThread == null) { //cek sunThread
          startTime = System.currentTimeMillis(); //mendata waktu pemprosesan
          sunThread = new Thread(() -> {
              if (timer == null) {
                  timer = new Timer();
                  timer.scheduleAtFixedRate(new TimerTask() {
                      @Override
                      public void run() {
                          long currentTime = itIsTime(); //waktu sejak dimulai % 200 detik
                          if (currentTime >= 200){currentTime = 0;} // atur agar tiap 200 detik ter set kembali ke 0
                          if (!shouldStopSunScore) {
                              if (currentTime >= 0 && currentTime < 100) {
                                  if (!isAfternoon) {
                                      Player.increaseSun();
                                    } else {
                                      shouldStopSunScore = true; // berhenti increase
                                    }
                                  currentCycle = (currentCycle + 1) % (cycleDuration * 2); // Update cycle counter

                                }
                            }
                        }
   
                    }, getRandomInterval(), getRandomInterval());
                }
            });
          sunThread.start(); // memulai thread
        }
    }
  
    public static void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (sunThread != null) {
            sunThread.interrupt(); // sinyal thread untuk berhenti
            try {
                sunThread.join(); // menunggu buat thread finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sunThread = null; // Clear reference
        }
    }

    public static void waitSunScore(int seconds) {
        try {
            long milliseconds = seconds * 1000;
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setIsAfternoon(boolean condition) {
        isAfternoon = condition;
    }

    private static long getRandomInterval() {
        return (long) (Math.random() * (10000 - 5000 + 1)) + 5000;
    }

    public static long itIsTime(){
      return (System.currentTimeMillis() - startTime)/1000;
    }
}
