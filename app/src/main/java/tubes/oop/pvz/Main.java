package tubes.oop.pvz;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Inventory inventory = new Inventory();
    public static Map map = new Map(9, 6);
    public static PlantDeck plantDeck = new PlantDeck(map);
    public static ListZombie listZombie = new ListZombie();
    public static Player player = new Player(25);
    public static Sun sun;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan;
            do {
                map.printMap();
                System.out.println("Pilih menu yang akan dijalankan:");
                System.out.println("1. Start Game");
                System.out.println("2. Help");
                System.out.println("3. Plant List");
                System.out.println("4. Zombie List");
                System.out.println("5. Exit");
                System.out.print("Masukkan pilihan: ");
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        startGame(scanner);
                        break;
                    case 2:
                        showHelp();
                        break;
                    case 3:
                        showPlantList();
                        break;
                    case 4:
                        showZombieList();
                        break;
                    case 5:
                        exitGame();
                        break;
                    default:
                        throw new InvalidInputMainException("Pilihan tidak valid. Silakan coba lagi.");
                }
            } while (pilihan != 5);
        } catch (InvalidInputMainException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void startGame(Scanner scanner) throws InvalidInputMainException{
        System.out.println("Berikut merupakan daftar inventory:");
        inventory.displayInventory();
        System.out.println("Apakah ingin masukkan tanaman ke dalam deck? (Y/N): ");
        String addDeck = scanner.nextLine();

        if (addDeck.equalsIgnoreCase("Y")) {
            System.out.println("Masukkan tanaman ke dalam deck (maksimal 6 tanaman)\n");
            System.out.println("Daftar deck tanaman saat ini :");

            plantDeck.printDeck();
            while(!(plantDeck.isDeckNotNull())){
                startDeck(scanner);
            }
        } 
        else {
            System.out.println("Tidak ada tanaman yang dimasukkan ke dalam deck.");
        }
        System.out.println("Game dimulai. Selamat bermain!");
        sun = new Sun();
        map.printMap();
        gameLoop(scanner);
    }
    
    public static void startDeck(Scanner scanner) throws InvalidInputMainException{
        while(!(plantDeck.isDeckNotNull())){
            System.out.println("Masukkan nomor tanaman untuk dimasukkan ke deck: ");
            int plantIndex = scanner.nextInt();
            scanner.nextLine();
            Plant plant = inventory.getSelectedPlant(plantIndex - 1);
            if (plant != null) {
                try {
                    for (int i = 0; i < 6; i++) {
                        if (plantDeck.getPlant(i) == null) {
                            plantDeck.addPlant(plant, i);
                            break;
                        }
                    }
                    plantDeck.printDeck();
                } catch (InvalidDeckException | InvalidIndexException e) {
                    e.printStackTrace();
                }
            } 
            else {
                System.out.println("Tanaman tidak ditemukan dalam inventory.");
            }
        }
        System.out.println("Apakah ingin menghapus deck? (Y/N)");
        String hapusDeck = scanner.nextLine();
        
        if(hapusDeck.equalsIgnoreCase("Y")){
            System.out.println("Nomor berapa yang ingin dihapus?");
            int hapusIndexDeck = scanner.nextInt();
            scanner.nextLine();
            try {
                plantDeck.removePlant(hapusIndexDeck-1);
            } catch (InvalidIndexException e) {
                e.printStackTrace();
            }
            plantDeck.printDeck();
        }
        else if(!(hapusDeck.equalsIgnoreCase("N"))){
            throw new InvalidInputMainException("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
       
    public static void gameLoop(Scanner scanner) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable timeGamePlay = new Runnable() {
            private int time = 0;

            @Override
            public void run() {
                time++;
                System.out.println("Waktu: " + time);
                if (time % 200 == 0) {
                    time = 0;
                }
                //map.printMap();
            }
        };

        Runnable spawnZombieTask = new Runnable() {
            private int time = 0;

            @Override
            public void run() {
                time++;
                if(time >= 20 && time <= 160) {
                    map.spawnRandomZombie(time);
                }
            }
        };

        Runnable sunTask = new Runnable() {
            @Override
            public void run() {
                sun.increaseSun(25);
                System.out.println("Sun: " + sun.getSunScore());
            }
        };

        scheduler.scheduleAtFixedRate(timeGamePlay, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(spawnZombieTask, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(sunTask, 0, 3, TimeUnit.SECONDS);

        while (!scheduler.isShutdown()) {
            System.out.println("Masukkan perintah (P/D/E): ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("E")) {
                System.out.println("Permainan berakhir.");
                scheduler.shutdown();
                sun.stopSunGeneration();
            } 
            else if (command.startsWith("P")) {
                String[] parts = command.split(" ");
                if (parts.length == 4) {
                    try {
                        int deckIndex = Integer.parseInt(parts[1]) - 1;
                        int x = Integer.parseInt(parts[2]);
                        int y = Integer.parseInt(parts[3]);
                        Plant plant = plantDeck.getPlant(deckIndex);
                        if (plant != null){
                            if(player.getSunScore() >= plant.getCostPlant()){
                                map.placePlant(plant, x-1, y-1);
                                System.out.println("Plant" + plant.getName() + "(" + x + ", " + y + ")");
                                System.out.println("Sun tersisa: " + sun.getSunScore());
                            }
                            else{
                                System.out.println("Sun tidak mencukupi untuk membeli tanaman");
                            }
                        } 
                        else {
                            System.out.println("Tanaman tidak ditemukan di deck");
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Perintah tidak valid.");
                    }
                } 
                else {
                    System.out.println("Perintah tidak valid.");
                }
            } 
            else if (command.startsWith("D")) {
                String[] parts = command.split(" ");
                if (parts.length == 3) {
                    try {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        map.getTile(x-1, y-1).removePlant();
                        System.out.println("Digging plant on (" + x + ", " + y + ")");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Perintah tidak valid.");
                    }
                } else {
                    System.out.println("Perintah tidak valid.");
                }
            } 
            else {
                System.out.println("Perintah tidak dikenal.");
            }

            // Cetak peta setelah setiap perintah
            map.printMap();
            
            // Check game over conditions
            // if (map.isZombieReachedEnd() || !map.isZombieSpawnPossible()) {
            //     System.out.println("Permainan berakhir.");
            //     scheduler.shutdown();
            //     sun.stopSunGeneration();
            // }
        }
    }

    public static void showHelp() {
        System.out.println("Help:"); // tambahin
    }

    public static void showPlantList() {
        System.out.println("\nDaftar Plant :\n");
        try {
            inventory.printPlantInfo();
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan daftar tanaman: " + e.getMessage());
        }
    }

    public static void showZombieList() {
        System.out.println("\nDaftar Zombie :\n");
        try {
            listZombie.printZombieInfo();
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan daftar zombie: " + e.getMessage());
        }
    }

    public static void exitGame() {
        System.out.println("Terima kasih telah bermain!");
    }
}

class InvalidInputMainException extends java.lang.Exception {
    public InvalidInputMainException(String message) {
        super(message);
    }
}
