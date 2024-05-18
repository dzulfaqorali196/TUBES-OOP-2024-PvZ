package tubes.oop.pvz;

import java.util.Scanner;

public class Main {
    public static Inventory inventory = new Inventory();
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan;
                do{
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
                        System.out.println("Game dimulai. Selamat bermain!");
                        System.out.println("Berikut merupakan daftar inventory:");
                        Inventory.displayInventory();
                        System.out.println("Apakah ingin masukkan tanaman ke dalam deck? (Y/N): ");
                        String addDeck = scanner.nextLine();
                        // scanner.nextLine();
                        if(addDeck.equals("Y")){
                            System.out.println("Masukkan tanaman ke dalam deck (maksimal 6 tanaman).");
                            while (PlantDeck.getSize() < 6) {
                                System.out.println("Masukkan nomor tanaman untuk dimasukkan ke deck: ");
                                int plantIndex = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                Plant plant = Inventory.getSelectedPlant(plantIndex - 1);
                                if (plant != null) {
                                    try {
                                        PlantDeck.addPlant(plant, plantIndex - 1);
                                    } catch (InvalidDeckException | InvalidIndexException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    throw new InvalidInputMainException("Tanaman tidak ditemukan dalam inventory.");
                                }
                            }
                            PlantDeck.printDeck();
                        }
                        else{
                            System.out.println("x");
                        }
                        startGame();
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
        } 
        catch (InvalidInputMainException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void startGame() {
        System.out.println("Game dimulai. Selamat bermain!");
    }

    public static void showHelp() {
        System.out.println("Help:");
        // Tambahkan informasi bantuan di sini
    }

    public static void showPlantList() {
        System.out.println("\nDaftar Plant :\n");
        Plant sunflower = new Sunflower(0, 0);
        Plant peaShooter = new PeaShooter(0, 0);
        Plant wallNut = new Wallnut(0, 0);
        Plant snowPea = new SnowPea(0, 0);
        Plant squash = new Squash(0, 0);
        Plant lilypad = new Lilypad(0, 0);
        Plant tallWallNut = new TallWallnut(0, 0);
        Plant repeaterPea = new RepeaterPea(0, 0);
        Plant jalapeno = new Jalapeno(0, 0);
        Plant tangleKelp = new TangleKelp(0, 0);

        PrintPlant.printPlantInfo(sunflower);
        PrintPlant.printPlantInfo(peaShooter);
        PrintPlant.printPlantInfo(wallNut);
        PrintPlant.printPlantInfo(snowPea);
        PrintPlant.printPlantInfo(squash);
        PrintPlant.printPlantInfo(lilypad);
        PrintPlant.printPlantInfo(tallWallNut);
        PrintPlant.printPlantInfo(repeaterPea);
        PrintPlant.printPlantInfo(jalapeno);
        PrintPlant.printPlantInfo(tangleKelp);
    }

    public static void showZombieList() {
        System.out.println("\nDaftar Zombie :\n");
        Zombie normalZombie = new NormalZombie(0, 0);
        Zombie coneHeadZombie = new ConeheadZombie(0, 0);
        Zombie poleVaultingZombie = new PoleVaultingZombie(0, 0);
        Zombie bucketHeadZombie = new BucketheadZombie(0, 0);
        Zombie duckyTubeZombie = new DuckyTubeZombie(0, 0);
        Zombie dolphinRiderZombie = new DolphinRiderZombie(0, 0);
        Zombie jesterZombie = new JesterZombie(0, 0);
        Zombie shieldZombie = new ShieldZombie(0, 0);
        Zombie giantZombie = new GiantZombie(0, 0);
        Zombie footballZombie = new FootballZombie(0, 0);

        PrintZombie.printZombieInfo(normalZombie);
        PrintZombie.printZombieInfo(coneHeadZombie);
        PrintZombie.printZombieInfo(poleVaultingZombie);
        PrintZombie.printZombieInfo(bucketHeadZombie);
        PrintZombie.printZombieInfo(duckyTubeZombie);
        PrintZombie.printZombieInfo(dolphinRiderZombie);
        PrintZombie.printZombieInfo(jesterZombie);
        PrintZombie.printZombieInfo(shieldZombie);
        PrintZombie.printZombieInfo(giantZombie);
        PrintZombie.printZombieInfo(footballZombie);
    }

    public static void exitGame() {
        System.out.println("Terima kasih telah bermain!");
    }
}

class InvalidInputMainException extends java.lang.Exception {
    private String message;

    public InvalidInputMainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}