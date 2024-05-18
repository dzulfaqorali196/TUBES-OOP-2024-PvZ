// package tubes.oop.pvz;

// import java.util.Scanner;

// public class Main {
//     public static int input;
//     public static void main(String[] args) throws InvalidDeckException, InvalidIndexException {
//         Map map = new Map(6, 9);
//         Inventory inventory = new Inventory();
//         PlantDeck plantDeck = new PlantDeck(map);
//         try (Scanner scanner = new Scanner(System.in)) {
//                 do{
//                 System.out.println("Pilih menu yang akan dijalankan:");
//                 System.out.println("1. Start Game");
//                 System.out.println("2. Help");
//                 System.out.println("3. Plant List");
//                 System.out.println("4. Zombie List");
//                 System.out.println("5. Exit");
//                 System.out.print("Masukkan pilihan: ");
//                 input = scanner.nextInt();
//                 scanner.nextLine(); 
                
//                 switch (input) {
//                     case 1:
//                         System.out.println("Game dimulai. Selamat bermain!");
//                         System.out.println("Berikut merupakan daftar inventory : ");
//                         Inventory.displayInventory();
//                         System.out.println("Apakah ingin masukkan tanaman ke dalam deck? (Y/N) : ");
                
//                         System.out.println("Masukkan tanaman ke dalam deck (maksimal 6 tanaman).");
//                         int index = 0;
//                         while (PlantDeck.getSize() < 6) {
//                             System.out.println("Masukkan nama tanaman untuk dimasukkan ke deck: ");
//                             input = scanner.nextInt();
//                             if (Inventory.getSelectedPlant(input-1) != null) {
//                                 PlantDeck.addPlant(Inventory.getSelectedPlant(input-1), index);
//                                 index++;
//                             } 
//                             else {
//                                 System.out.println("Tanaman tidak ditemukan dalam inventory.");
//                             }
//                         }
//                         PlantDeck.printDeck();
//                         break;
//                     case 2:
//                         showHelp();
//                         break;
//                     case 3:
//                         showPlantList();
//                         break;
//                     case 4:
//                         showZombieList();
//                         break;
//                     case 5:
//                         exitGame();
//                         break;
//                     default:
//                         throw new InvalidInputMainException("Pilihan tidak valid. Silakan coba lagi.");
//                 }
//             } while (input != 5);
//         } catch (InvalidInputMainException e) {
//             System.err.println(e.getMessage());
//         }
//     }

//     public static void showHelp() {
//         System.out.println("Help:");
//         // Tambahkan informasi bantuan di sini
//     }

//     public static void showPlantList() {
//         System.out.println("\nDaftar Plant :\n");
//         Plant sunflower = new Sunflower(0, 0);
//         Plant peaShooter = new PeaShooter(0, 0);
//         Plant wallNut = new Wallnut(0, 0);
//         Plant snowPea = new SnowPea(0, 0);
//         Plant squash = new Squash(0, 0);
//         Plant lilypad = new Lilypad(0, 0);
//         Plant tallWallNut = new TallWallnut(0, 0);
//         Plant repeaterPea = new RepeaterPea(0, 0);
//         Plant jalapeno = new Jalapeno(0, 0);
//         Plant tangleKelp = new TangleKelp(0, 0);

//         PrintPlant.printPlantInfo(sunflower);
//         PrintPlant.printPlantInfo(peaShooter);
//         PrintPlant.printPlantInfo(wallNut);
//         PrintPlant.printPlantInfo(snowPea);
//         PrintPlant.printPlantInfo(squash);
//         PrintPlant.printPlantInfo(lilypad);
//         PrintPlant.printPlantInfo(tallWallNut);
//         PrintPlant.printPlantInfo(repeaterPea);
//         PrintPlant.printPlantInfo(jalapeno);
//         PrintPlant.printPlantInfo(tangleKelp);
//     }

//     public static void showZombieList() {
//         System.out.println("\nDaftar Zombie :\n");
//         Zombie normalZombie = new NormalZombie(0, 0);
//         Zombie coneHeadZombie = new ConeheadZombie(0, 0);
//         Zombie poleVaultingZombie = new PoleVaultingZombie(0, 0);
//         Zombie bucketHeadZombie = new BucketheadZombie(0, 0);
//         Zombie duckyTubeZombie = new DuckyTubeZombie(0, 0);
//         Zombie dolphinRiderZombie = new DolphinRiderZombie(0, 0);
//         Zombie jesterZombie = new JesterZombie(0, 0);
//         Zombie shieldZombie = new ShieldZombie(0, 0);
//         Zombie giantZombie = new GiantZombie(0, 0);
//         Zombie footballZombie = new FootballZombie(0, 0);

//         PrintZombie.printZombieInfo(normalZombie);
//         PrintZombie.printZombieInfo(coneHeadZombie);
//         PrintZombie.printZombieInfo(poleVaultingZombie);
//         PrintZombie.printZombieInfo(bucketHeadZombie);
//         PrintZombie.printZombieInfo(duckyTubeZombie);
//         PrintZombie.printZombieInfo(dolphinRiderZombie);
//         PrintZombie.printZombieInfo(jesterZombie);
//         PrintZombie.printZombieInfo(shieldZombie);
//         PrintZombie.printZombieInfo(giantZombie);
//         PrintZombie.printZombieInfo(footballZombie);
//     }

//     public static void exitGame() {
//         System.out.println("Terima kasih telah bermain!");
//     }
// }
