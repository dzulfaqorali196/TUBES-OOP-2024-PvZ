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

    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";

    public static void main(String[] args) throws InvalidDeckException{
        String michael = GREEN +
                "    ███▄ ▄███▓ ██▓ ▄████▄   ██░ ██  ▄▄▄      ▓█████  ██▓           \n" +
                "   ▓██▒▀█▀ ██▒▓██▒▒██▀ ▀█  ▓██░ ██▒▒████▄    ▓█   ▀ ▓██▒           \n" +
                "   ▓██    ▓██░▒██▒▒▓█    ▄ ▒██▀▀██░▒██  ▀█▄  ▒███   ▒██░           \n" +
                "   ▒██    ▒██ ░██░▒▓▓▄ ▄██▒░▓█ ░██ ░██▄▄▄▄██ ▒▓█  ▄ ▒██░           \n" +
                "   ▒██▒   ░██▒░██░▒ ▓███▀ ░░▓█▒░██▓ ▓█   ▓██▒░▒████▒░██████▒       \n" +
                "   ░ ▒░   ░  ░░▓  ░ ░▒ ▒  ░ ▒ ░░▒░▒ ▒▒   ▓▒█░░░ ▒░ ░░ ▒░▓  ░       \n" +
                "   ░  ░      ░ ▒ ░  ░  ▒    ▒ ░▒░ ░  ▒   ▒▒ ░ ░ ░  ░░ ░ ▒  ░       \n" +
                "   ░      ░    ▒ ░░         ░  ░░ ░  ░   ▒      ░     ░ ░          \n" +
                "          ░    ░  ░ ░       ░  ░  ░      ░  ░   ░  ░    ░  ░       \n" +
                "                  ░                                                \n" + RESET;
        String vs = RED +        
                "                     ██▒   █▓  ██████                                                \n" +
                "                    ▓██░   █▒▒██    ▒                                                \n" +
                "                     ▓██  █▒░░ ▓██▄                                                 \n" +
                "                      ▒██ █░░  ▒   ██▒                                               \n" +
                "                       ▒▀█░  ▒██████▒▒                                               \n" +
                "                       ░ ▐░  ▒ ▒▓▒ ▒ ░                                               \n" +
                "                       ░ ░░  ░ ░▒  ░ ░                                               \n" +
                "                         ░░  ░  ░  ░                                                 \n" +
                "                          ░        ░                                                 \n" +
                "                         ░                                                           \n" + RESET;
        String lalapan = GREEN +
                " ██▓    ▄▄▄       ██▓    ▄▄▄       ██▓███   ▄▄▄       ███▄    █ \n" +
                "▓██▒   ▒████▄    ▓██▒   ▒████▄    ▓██░  ██▒▒████▄     ██ ▀█   █ \n" +
                "▒██░   ▒██  ▀█▄  ▒██░   ▒██  ▀█▄  ▓██░ ██▓▒▒██  ▀█▄  ▓██  ▀█ ██▒\n" +
                "▒██░   ░██▄▄▄▄██ ▒██░   ░██▄▄▄▄██ ▒██▄█▓▒ ▒░██▄▄▄▄██ ▓██▒  ▐▌██▒\n" +
                "░██████▒▓█   ▓██▒░██████▒▓█   ▓██▒▒██▒ ░  ░ ▓█   ▓██▒▒██░   ▓██░\n" +
                "░ ▒░▓  ░▒▒   ▓▒█░░ ▒░▓  ░▒▒   ▓▒█░▒▓▒░ ░  ░ ▒▒   ▓▒█░░ ▒░   ▒ ▒ \n" +
                "░ ░ ▒  ░ ▒   ▒▒ ░░ ░ ▒  ░ ▒   ▒▒ ░░▒ ░       ▒   ▒▒ ░░ ░░   ░ ▒░\n" +
                "  ░ ░    ░   ▒     ░ ░    ░   ▒   ░░         ░   ▒      ░   ░ ░ \n" +
                "    ░  ░     ░  ░    ░  ░     ░  ░               ░  ░         ░ \n" + RESET;

        System.out.print(michael + vs + lalapan);

        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan = 1;
            do {
                boolean inputValid;
                do{
                    if(pilihan < 1 || pilihan > 5){
                        System.out.println("Masukkan hanya angka 1-5, perintah tidak valid!");
                    }
                    String leftAlignFormat = "| %-25s | %-70s |%n";
                    String separator = "+---------------------------+------------------------------------------------------------------------+%n";
                
                    String[] penjelasanMenu = {
                        "  ___           _     _                      __  __              ",
                        " | _ \\___ _ _  (_)___| |__ _ ___ __ _ _ _   |  \\/  |___ _ _ _  _ ",
                        " |  _/ -_) ' \\ | / -_) / _` (_-</ _` | ' \\  | |\\/| / -_) ' \\ || |",
                        " |_| \\___|_||_|/ \\___|_\\__,_/__/\\__,_|_||_| |_|  |_\\___|_||_\\_,_|",
                        "             |__/                                                "
                    };

                    for (String line : penjelasanMenu) {
                        System.out.println(GREEN + line + RESET);
                    }
                    System.out.format(separator);
                    System.out.format("| Menu                      | Deskripsi                                                              |%n");
                    System.out.format(separator);
                    System.out.format(leftAlignFormat, "1. Start Game", "Memulai permainan dan memilih tanaman untuk deck.");
                    System.out.format(separator);
                    System.out.format(leftAlignFormat, "2. Help", "Menampilkan menu bantuan ini.");
                    System.out.format(separator);
                    System.out.format(leftAlignFormat, "3. Plant List", "Menampilkan daftar tanaman yang bisa digunakan dalam permainan.");
                    System.out.format(separator);
                    System.out.format(leftAlignFormat, "4. Zombie List", "Menampilkan daftar zombie yang akan muncul dalam permainan.");
                    System.out.format(separator);
                    System.out.format(leftAlignFormat, "5. Exit", "Keluar dari permainan.");
                    System.out.format(separator);
                    System.out.print("Masukkan pilihan: ");
                    
                    if (scanner.hasNextInt()) {
                        pilihan = scanner.nextInt();
                        scanner.nextLine();  // Consume newline character
                        inputValid = (pilihan >= 1 && pilihan <= 5);
                        if (!inputValid) {
                            System.out.println("Masukkan hanya angka 1-5, perintah tidak valid!");
                        }
                    } else {
                        System.out.println("Masukkan hanya angka 1-5, perintah tidak valid!");
                        scanner.nextLine();  // Clear the invalid input
                        inputValid = false;
                    }
                } while(!inputValid);

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
                        startDeck(scanner);
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

        String swapDeck;

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
                    if((1 <= x && x <= 10) && (1 <= y && y <= 10) && (x != y)){
                        try {
                            plantDeck.swapPlantDeckMain((x-1), (y-1));
                        } 
                        catch (InvalidIndexException e) {
                            e.printStackTrace();
                        }
                        plantDeck.printDeck();
                    }
                    else{
                        System.out.println("Masukkan hanya angka 1-10 dan tidak boleh sama, perintah tidak valid!");
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

        System.out.println("Selamat bermain!");
        gameLoop(scanner);
    }

    public static void gameLoop(Scanner scanner) {
        map.gameStart();
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
                        if((time % 200) <= 100){
                            player.increaseSun(25); 
                        }
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
                                
                                // System.out.println("Plant" + plant.getName() + "(" + x + ", " + y + ")");
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

            map.printMap();

            
            if (map.getTotalZombie() <= 0 && time > 160){
                System.out.println("Permainan berakhir, kamu menang!");
                scheduler.shutdown();
                sun.stopSunGeneration();
            }
            else if(zombieWin()){
                System.out.println("Permainan berakhir, kamu kalah");
                scheduler.shutdown();
                sun.stopSunGeneration();
                map.stopGame();
            }

            // main();
            
            // if (zombieLose()) {
            //     System.out.println("Player menang! Semua zombie telah dikalahkan.");
            //     scheduler.shutdown();
            // }
            // if (zombieWin()) {
            //     System.out.println("Player kalah! Salah satu zombie telah mencapai kolom pertama.");
            //     scheduler.shutdown();
            // }

            // if (map.isZombieReachedEnd() || !map.isZombieSpawnPossible()) {
            //     System.out.println("Permainan berakhir.");
            //     scheduler.shutdown();
            //     sun.stopSunGeneration();
            // }
        }
    }

    // private static boolean zombieLose() {
    //     for (int y = 0; y < 6; y++) {
    //         for (int x = 0; x < 11; x++)  {
    //             Tile tile = map.getTile(y, x);
    //             if (!tile.noZombie()) {
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

    public class Task implements Runnable {
        private volatile boolean running = true;
    
        public void run() {
            while (running) {
                // Lakukan pekerjaan
            }
        }
    
        public void stop() {
            running = false;
        }
    }
    
    public static boolean zombieWin() {
        for (int i = 0; i < 6; i++) {
            Tile tile = map.getTile(0, i);
            if (!tile.getZombie().isEmpty()) { 
                return true;
            }
        }
        return false;
    }

    public static void showHelp() {
        String leftAlignFormat = "| %-25s | %-70s |%n";
        String separator = "+---------------------------+------------------------------------------------------------------------+%n";
        String[] deskripsiPermainan = {
            "  ___         _       _         _   ___                    _                ",
            " |   \\ ___ __| |___ _(_)_ __ __(_) | _ \\___ _ _ _ __  __ _(_)_ _  __ _ _ _  ",
            " | |) / -_|_-< / / '_| | '_ (_-< | |  _/ -_) '_| '  \\/ _` | | ' \\/ _` | ' \\ ",
            " |___/\\___/__/_\\_\\_| |_| .__/__/_| |_| \\___|_| |_|_|_\\__,_|_|_||_\\__,_|_||_|",
            "                       |_|                                                  "
        };

        for (String line : deskripsiPermainan) {
            System.out.println(RED + line + RESET);
        }
        System.out.format(separator);
        printWrappedText("Deskripsi", "Michael vs. Lalapan adalah permainan bertahan hidup di mana pemain harus melindungi rumah dari serangan zombie. Pemain menggunakan berbagai jenis tanaman yang memiliki kemampuan khusus untuk menghalangi dan mengalahkan zombie yang datang dari sisi kanan peta.");
        System.out.format(separator);
    
        String[] caraBermain = {
            "   ___                 ___                     _      ",
            "  / __|__ _ _ _ __ _  | _ ) ___ _ _ _ __  __ _(_)_ _  ",
            " | (__/ _` | '_/ _` | | _ \\/ -_) '_| '  \\/ _` | | ' \\ ",
            "  \\___\\__,_|_| \\__,_| |___/\\___|_| |_|_|_\\__,_|_|_||_|",
            "                                                      "
        };

        for (String line : caraBermain) {
            System.out.println(GREEN + line + RESET);
        }
        System.out.format(separator);
        printWrappedText("Memulai Permainan", "Pilih opsi 'Start Game' dari menu utama. Anda akan diberikan pilihan untuk menukar tanaman di inventory sebelum permainan dimulai. Pilih tanaman untuk dimasukkan ke dalam deck. Deck hanya bisa menampung maksimal 6 tanaman.");
        System.out.format(separator);
        printWrappedText("Menanam Tanaman", "Gunakan perintah 'P x y z' untuk menanam tanaman dari deck ke peta. Pastikan Anda memiliki cukup sun untuk menanam tanaman tersebut.");
        System.out.format(separator);
        printWrappedText("Menggali Tanaman", "Gunakan perintah 'D x y' untuk menggali dan menghapus tanaman dari peta.");
        System.out.format(separator);
        printWrappedText("Menghasilkan Sun", "Tanaman Sunflower akan menghasilkan sun secara berkala. Sun juga bisa didapatkan secara acak saat pagi hari.");
        System.out.format(separator);
        printWrappedText("Menangani Zombie", "Zombie akan muncul secara berkala dari sisi kanan peta. Setiap zombie memiliki atribut health, attack damage, attack speed, dan kemampuan khusus. Gunakan tanaman yang tepat untuk menghalangi dan mengalahkan zombie sebelum mereka mencapai sisi kiri peta.");
        System.out.format(separator);
    
        String[] peraturanPermainan = {
            "  ___              _                        ___                    _                ",
            " | _ \\___ _ _ __ _| |_ _  _ _ _ __ _ _ _   | _ \\___ _ _ _ __  __ _(_)_ _  __ _ _ _  ",
            " |  _/ -_) '_/ _` |  _| || | '_/ _` | ' \\  |  _/ -_) '_| '  \\/ _` | | ' \\/ _` | ' \\ ",
            " |_| \\___|_| \\__,_|\\__|\\_,_|_| \\__,_|_||_| |_| \\___|_| |_|_|_\\__,_|_|_||_\\__,_|_||_|",
            "                                                                                   "
        };

        for (String line : peraturanPermainan) {
            System.out.println(RED + line + RESET);
        }
        System.out.format(separator);
        printWrappedText("Tanaman", "Setiap tanaman memiliki atribut: Name, Cost, Health, Attack Damage, Attack Speed, Range, Cooldown.");
        System.out.format(separator);
        printWrappedText("Zombie", "Setiap zombie memiliki atribut: Name, Health, Attack Damage, Attack Speed, Is Aquatic.");
        System.out.format(separator);
        printWrappedText("Strategi", "Pemain harus mengatur strategi dengan bijak: Tanam tanaman di petak yang strategis untuk menghentikan zombie. Kelola sun dengan bijak untuk memastikan Anda memiliki cukup sun untuk menanam tanaman saat dibutuhkan.");
        System.out.format(separator);
    
        String[] selamatBermain = {
            "  _  __            _ _    _     _   _   _    _       ___                    _                ",
            " | |/ /___ _ _  __| (_)__(_)   /_\\ | |_| |_ (_)_ _  | _ \\___ _ _ _ __  __ _(_)_ _  __ _ _ _  ",
            " | ' </ _ \\ ' \\/ _` | (_-< |  / _ \\| / / ' \\| | '_| |  _/ -_) '_| '  \\/ _` | | ' \\/ _` | ' \\ ",
            " |_|\\_\\___/_||_\\__,_|_/__/_| /_/ \\_\\_\\_\\_||_|_|_|   |_| \\___|_| |_|_|_\\__,_|_|_||_\\__,_|_||_|",
            "                                                                                             "
        };

        for (String line : selamatBermain) {
            System.out.println(GREEN + line + RESET);
        }
        System.out.format(separator);
        printWrappedText("Kondisi Menang", "Pemain menang jika semua zombie telah dikalahkan.");
        System.out.format(separator);
        printWrappedText("Kondisi Kalah", "Pemain kalah jika salah satu zombie mencapai kolom pertama pada peta permainan.");
        System.out.format(separator);
    
        String[] art = {
            "  ___      _                 _     ___                     _      _ ",
            " / __| ___| |__ _ _ __  __ _| |_  | _ ) ___ _ _ _ __  __ _(_)_ _ | |",
            " \\__ \\/ -_) / _` | '  \\/ _` |  _| | _ \\/ -_) '_| '  \\/ _` | | ' \\|_|",
            " |___/\\___|_\\__,_|_|_|_\\__,_|\\__| |___/\\___|_| |_|_|_\\__,_|_|_||_(_)",
            "                                                                    "
        };

        for (String line : art) {
            System.out.println(RED + line + RESET);
        }
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

    private static void printWrappedText(String header, String text) {
        int maxLineLength = 70;
        while (text.length() > maxLineLength) {
            int splitPos = text.lastIndexOf(' ', maxLineLength);
            if (splitPos == -1) splitPos = maxLineLength;
            System.out.format("| %-25s | %-70s |%n", header, text.substring(0, splitPos));
            text = text.substring(splitPos).trim();
            header = "";
        }
        System.out.format("| %-25s | %-70s |%n", header, text);
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