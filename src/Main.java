import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //ganti kata katanya
        System.out.println("Pilih menu yang akan dijalankan :");
        System.out.println("1. Start Game");
        System.out.println("2. Help");
        System.out.println("3. Plant List");
        System.out.println("4. Zombie List :");
        System.out.println("5. Exit :");
        Scanner input = new Scanner(System.in);
        int menu = input.nextInt();
        
        switch(menu){
            case 1:
                startGame();
                break;
            case 2:
                helpGame();
                break;  
            case 3:
                displayInventory();
                break;
            case 4:
                displayZombieList();
                break;
            case 5:
                exitGame();
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan pilih menu 1-5.");
        }

        input.close();
    }

    private static void startGame() {
        System.out.println("Game dimulai. Selamat bermain!");
    }

    private static void helpGame() {
        System.out.println("Help Game :");
        System.out.println("xxxxx");
    }

    private static void displayZombieList() {
        System.out.println("Daftar Zombie:");
        System.out.println("xxxxx");
    }

    private static void exitGame() {
        System.out.println("Terima kasih telah bermain!");
        System.exit(0);
    }   
}
