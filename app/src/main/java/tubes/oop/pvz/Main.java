package tubes.oop.pvz;

import java.util.Scanner;

public class Main {
    public static Inventory inventory = new Inventory();
    public static Map gameMap = new Map(9, 6);
    public static PlantDeck plantDeck = new PlantDeck(gameMap);
    public static ListZombie listZombie = new ListZombie();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan;
            do {
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

    public static void startGame(Scanner scanner) {
        System.out.println("Game dimulai. Selamat bermain!");
        System.out.println("Berikut merupakan daftar inventory:");
        inventory.displayInventory();
        System.out.println("Apakah ingin masukkan tanaman ke dalam deck? (Y/N): ");
        String addDeck = scanner.nextLine();
        if (addDeck.equalsIgnoreCase("Y")) {
            System.out.println("Masukkan tanaman ke dalam deck (maksimal 6 tanaman).");
            while (plantDeck.getSize() < 6) {
                System.out.println("Masukkan nomor tanaman untuk dimasukkan ke deck: ");
                int plantIndex = scanner.nextInt();
                scanner.nextLine();
                Plant plant = inventory.getSelectedPlant(plantIndex - 1);
                if (plant != null) {
                    try {
                        plantDeck.addPlant(plant, plantDeck.getSize());
                    } catch (InvalidDeckException | InvalidIndexException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Tanaman tidak ditemukan dalam inventory.");
                }
            }
            plantDeck.printDeck();
        } else {
            System.out.println("Tidak ada tanaman yang dimasukkan ke dalam deck.");
        }
    }

    public static void showHelp() {
        System.out.println("Help:");
        // Tambahkan informasi bantuan di sini
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
