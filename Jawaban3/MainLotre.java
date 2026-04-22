import java.util.Scanner;

public class MainLotre {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("  Welcome to E-Lottery Gosok");
        System.out.println("=================================");
        System.out.println("Aturan Permainan:");
        System.out.println("- Papan berukuran 4 baris x 5 kolom");
        System.out.println("- Terdapat 2 bom dan 18 kotak aman");
        System.out.println("- Tebak posisi (baris dan kolom) untuk membuka kotak");
        System.out.println("- Jika kena bom, permainan berakhir");
        System.out.println("- Jika membuka semua kotak aman, Anda MENANG!");
        System.out.println("=================================");

        boolean mainLagi = true;

        while (mainLagi) {
            LotreBoard game = new LotreBoard();

            System.out.println("\nPapan Lotre:");
            game.displayBoardSederhana();

            while (!game.isGameOver()) {
                System.out.print("Masukkan tebakan anda (baris dan kolom): ");

                int baris = -1;
                int kolom = -1;

                if (scanner.hasNextInt()) {
                    baris = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        kolom = scanner.nextInt();
                    } else {
                        System.out.println("Input tidak valid! Masukkan dua angka (baris dan kolom).");
                        scanner.next();
                        continue;
                    }
                } else {
                    System.out.println("Input tidak valid! Masukkan dua angka (baris dan kolom).");
                    scanner.next();
                    continue;
                }

                boolean aman = game.guess(baris, kolom);

                if (!game.isGameOver()) {
                    game.displayBoardSederhana();
                } else {
                    if (game.isWin()) {
                        System.out.println("\nSELAMAT! Anda berhasil membuka semua kotak aman!");
                    }
                    game.displayFinalBoard();
                }
            }

            System.out.println("\n=== STATISTIK PERMAINAN ===");
            System.out.println("Kotak aman terbuka: " + game.getKotakAmanTerbuka() +
                    " dari " + game.getJumlahAman());
            if (game.isWin()) {
                System.out.println("Hasil: MENANG! 🎉");
            } else {
                System.out.println("Hasil: KALAH! 💣");
            }

            System.out.print("\nMain lagi? (y/n): ");
            scanner.nextLine();
            String jawaban = scanner.nextLine();
            if (!jawaban.equalsIgnoreCase("y")) {
                mainLagi = false;
            }
        }

        System.out.println("\nTerima kasih telah bermain E-Lottery Gosok!");
        scanner.close();
    }
}