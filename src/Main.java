import java.util.Scanner;

public class Main {
    public static void Main(String[] args){
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
                // startGame();
                break;
            case 2:
                // helpGame();
                break;  
            case 3:
                // displayInventory();
                break;
            case 4:
                // displayZombieList();
                break;
            case 5:
                // exitGame();
                break;  
        }
    }   
}
