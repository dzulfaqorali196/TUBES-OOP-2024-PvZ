package tubes.oop.pvz;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Map map = new Map(11, 6);
    public static Inventory inventory = new Inventory();
    public static PlantDeck plantDeck = new PlantDeck(map);
    public static ListZombie listZombie = new ListZombie();
    public static Player player = new Player();
    public static Sun sun;
    private static int time = 0;



    public static void main(String[] args) throws InvalidDeckException{
        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan = 1;
            do {
                do{
                    if(pilihan < 1 || pilihan > 5){
                        System.out.println("Masukkan hanya angka 1-5, perintah tidak valid!");
                    }
                    System.out.println("Pilih menu yang akan dijalankan:");
                    System.out.println("1. Start Game");
                    System.out.println("2. Help");
                    System.out.println("3. Plant List");
                    System.out.println("4. Zombie List");
                    System.out.println("5. Exit");
                    System.out.print("Masukkan pilihan: ");
                    pilihan = scanner.nextInt();
                    scanner.nextLine();
                } while(pilihan < 1 || pilihan > 5);

                switch (pilihan) {
                    case 1:
                        inventoryGame(scanner);
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

    public static void inventoryGame(Scanner scanner) throws InvalidInputMainException, InvalidDeckException{
        System.out.println("Berikut merupakan daftar inventory:");
        inventory.displayInventory();
        String swapInvent;

        do {
            System.out.println("Apakah Anda ingin menukar inventory? (Y/N)");
            swapInvent = scanner.nextLine();
            if (swapInvent.equals("Y")) {
                System.out.println("Masukkan nomor yang ingin ditukar (x y)");
                swapInvent = scanner.nextLine();
                String[] sukuString = swapInvent.split(" ");
                if(sukuString.length == 2){
                    int x = Integer.parseInt(sukuString[0]);
                    int y = Integer.parseInt(sukuString[1]);
                    if((1 <= x && x <= 10) && (1 <= y && y <= 10) && (x != y)){
                        try {
                            inventory.swapPlantInventory((x-1), (y-1));
                        } 
                        catch (InvalidIndexException e) {
                            e.printStackTrace();
                        }
                        inventory.displayInventory();
                    }
                    else{
                        System.out.println("Masukkan hanya angka 1-10, perintah tidak valid!");
                    }
                }
                else{
                    System.out.println("Masukkan hanya 2 angka (x, y), perintah tidak valid!");
                }
            } 
            else if (swapInvent.equals("N")) {
                break;
            }
            else{
                System.out.println("Masukkan hanya huruf Y atau N, perintah tidak valid!");
            }
        } while (!swapInvent.equals("Y") && !swapInvent.equals("N"));
        deckGame(scanner);
    }

    public static void deckGame(Scanner scanner) throws InvalidInputMainException, InvalidDeckException{
        String addDeck;
        String swapDeck;

        do {
            System.out.println("Apakah ingin masukkan tanaman ke dalam deck? (Y/N): ");
            addDeck = scanner.nextLine();
            if (addDeck.equalsIgnoreCase("Y")) {
                System.out.println("Masukkan tanaman ke dalam deck (maksimal 6 tanaman)\n");
                System.out.println("Daftar deck tanaman saat ini :");
    
                plantDeck.printDeck();
                while(!(plantDeck.isDeckNotNull())){
                    startDeck(scanner);
                }

                do {
                    System.out.println("Apakah Anda ingin menukar plant deck? (Y/N)");
                    swapDeck = scanner.nextLine();
                    if (swapDeck.equals("Y")) {
                        System.out.println("Masukkan nomor yang ingin ditukar (x y)");
                        swapDeck = scanner.nextLine();
                        String[] sukuString = swapDeck.split(" ");
                        if(sukuString.length == 2){
                            int x = Integer.parseInt(sukuString[0]);
                            int y = Integer.parseInt(sukuString[1]);
                            if((1 <= x && x <= 6) && (1 <= y && y <= 6) && (x != y)){
                                try {
                                    plantDeck.swapPlantDeck((x-1), (y-1));
                                } 
                                catch (InvalidIndexException e) {
                                    e.printStackTrace();
                                }
                                plantDeck.printDeck();
                            }
                            else{
                                System.out.println("Masukkan hanya angka 1-6, perintah tidak valid!");
                            }
                        }
                        else{
                            System.out.println("Masukkan hanya 2 angka (x, y), perintah tidak valid!");
                        }
                    } 
                    else if (swapDeck.equals("N")) {
                        break;
                    }
                    else{
                        System.out.println("Masukkan hanya huruf Y atau N, perintah tidak valid!");
                    }
                } while (!swapDeck.equals("Y") && !swapDeck.equals("N"));
            } 
        } while (!addDeck.equals("Y") && !addDeck.equals("N") || !(plantDeck.isDeckNotNull()));
        gameLoop(scanner);
    }
    
    public static void startDeck(Scanner scanner) throws InvalidInputMainException, InvalidDeckException{
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

        String hapusDeck;
        int hapusDeckIndex;
        
        do {
            System.out.println("Apakah ingin menghapus deck? (Y/N)");
            hapusDeck = scanner.nextLine();
            if (hapusDeck.equals("Y")) {
                System.out.println("Masukkan nomor yang ingin dihapus (x)");
                hapusDeckIndex = scanner.nextInt();
                if(hapusDeckIndex > 0 && hapusDeckIndex <= 6){
                    try {
                        plantDeck.removePlant(hapusDeckIndex-1);
                        plantDeck.printDeck();
                    } 
                    catch (InvalidIndexException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Masukkan hanya angka 1-6, perintah tidak valid!");
                }
            }
            else if (hapusDeck.equals("N")){
                break;
            } 
            else{
                System.out.println("Masukkan hanya huruf Y atau N, perintah tidak valid!");
            }
        } while (!hapusDeck.equals("Y") && !hapusDeck.equals("N"));

        System.out.println("Selamat bermain!");
        gameLoop(scanner);
    }

    public static void gameLoop(Scanner scanner) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable timeGamePlay = new Runnable() {
            //private int time = 0;

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

        //NGUBAH INI, NYOBA
        // Runnable spawnZombieTask = new Runnable() {
        //     // private int time = 0;

        //     @Override
        //     public void run() {
        //         if(time >= 20 && time <= 160) {
        //             map.startSpawnZombie();
        //         }
        //     }
        // };

        Runnable sunTask = new Runnable() {
            @Override
            public void run() {
                int delay = 5 + (int)(Math.random() * ((10 - 5) + 1)); 
                scheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        player.increaseSun(25); 
                        System.out.println("Sun: " + Player.getSunScore());
                    }
                }, delay, TimeUnit.SECONDS);
            }
        };

        // Runnable attackTask = new Runnable() {
        //     @Override
        //     public void run() {
        //         for (int x = 0; x < 6; x++) {
        //             for (int y = 0; y < 11; y++) {
        //                 Tile tile = map.getTile(y, x);
        //                 Plant plant = tile.getPlant();
        //                 if (map != null) {
        //                     plant.getZombieInRange(map);
        //                 }
        //             }
        //         }                //map.moveZombies();
        //     }
        // };

        // Runnable removeDeadZombiesTask = new Runnable() {
        //     @Override
        //     public void run() {
        //         map.removeZombieMap();
        //     }
        // };

        scheduler.scheduleAtFixedRate(timeGamePlay, 0, 1, TimeUnit.SECONDS);
        // scheduler.scheduleAtFixedRate(spawnZombieTask, 0, 3, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(sunTask, 0, 10, TimeUnit.SECONDS);
        // scheduler.scheduleAtFixedRate(attackTask, 0, 1, TimeUnit.SECONDS);
        // scheduler.scheduleAtFixedRate(removeDeadZombiesTask, 0, 1, TimeUnit.SECONDS);

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
                        int deckIndex = Integer.parseInt(parts[1]);
                        int x = Integer.parseInt(parts[2]);
                        int y = Integer.parseInt(parts[3]);
                        Plant plant = plantDeck.getPlant(deckIndex-1);
                        if (plant != null){
                            if(Player.getSunScore() >= plant.getCostPlant()){
                                map.placePlant(plant, (x), (y-1));
                                
                                System.out.println("Plant" + plant.getName() + "(" + x + ", " + y + ")");
                                System.out.println("Sun tersisa: " + Sun.getSunScore());
                            }
                            else{
                                System.out.println("Sun tidak mencukupi untuk membeli tanaman");
                            }
                        } 
                        else {
                            System.out.println("Tanaman tidak ditemukan di deck");
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Perintah tidak valid.A");
                    }
                } 
                else {
                    System.out.println("Perintah tidak valid.B");
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