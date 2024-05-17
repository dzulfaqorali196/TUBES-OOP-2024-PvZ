package tubes.oop.pvz;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Pilih menu yang akan dijalankan:");
            System.out.println("1. Start Game");
            System.out.println("2. Help");
            System.out.println("3. Plant List");
            System.out.println("4. Zombie List");
            System.out.println("5. Exit");
            System.out.print("Masukkan pilihan (gunakan spasi untuk memisahkan beberapa pilihan): ");

            String fullInput = input.nextLine();
            String[] commands = fullInput.split("\\s+");

            for (String menu : commands) {
                switch (menu) {
                    case "1":
                        startGame();
                        break;
                    case "2":
                        helpGame();
                        break;
                    case "3":
                        System.out.println("Plant List:");
                        break;
                    case "4":
                        displayZombieList();
                        break;
                    case "5":
                        exitGame();
                        return; // Menyelesaikan loop dan keluar dari program
                    default:
                        System.out.println("Pilihan tidak valid: " + menu + ". Silakan pilih menu 1-5.");
                }
                System.out.println(); // Menambah baris kosong untuk memisahkan menu selanjutnya
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Harap masukkan angka.");
        } catch (NoSuchElementException e) {
            System.out.println("Input tidak ditemukan. Harap masukkan pilihan menu.");
        }
    }

    private static void startGame() {
        System.out.println("Game dimulai. Selamat bermain!");
    }

    private static void helpGame() {
        System.out.println("Help Game:");
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

    static class InvalidInputException extends java.lang.Exception {
        private String message;

        public InvalidInputException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
