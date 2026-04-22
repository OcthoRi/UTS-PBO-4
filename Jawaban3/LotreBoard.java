import java.util.Random;

public class LotreBoard {

    private static final int BARIS = 4;
    private static final int KOLOM = 5;
    private static final int JUMLAH_BOM = 2;
    private static final int TOTAL_KOTAK = BARIS * KOLOM;
    private static final int JUMLAH_AMAN = TOTAL_KOTAK - JUMLAH_BOM;

    private char[][] board;
    private boolean[][] revealed;
    private int[][] data;
    private boolean gameOver;
    private boolean win;
    private int kotakAmanTerbuka;

    public LotreBoard() {
        board = new char[BARIS][KOLOM];
        revealed = new boolean[BARIS][KOLOM];
        data = new int[BARIS][KOLOM];
        gameOver = false;
        win = false;
        kotakAmanTerbuka = 0;

        generateBoard();
        inisialisasiTampilan();
    }

    public void generateBoard() {
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                data[i][j] = 0;
            }
        }

        Random random = new Random();
        int bomDitempatkan = 0;

        while (bomDitempatkan < JUMLAH_BOM) {
            int barisAcak = random.nextInt(BARIS);
            int kolomAcak = random.nextInt(KOLOM);

            if (data[barisAcak][kolomAcak] != 1) {
                data[barisAcak][kolomAcak] = 1;
                bomDitempatkan++;
            }
        }
    }

    private void inisialisasiTampilan() {
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                board[i][j] = '*';
            }
        }
    }

    public void displayBoard() {
        System.out.println();

        System.out.print("   ");
        for (int j = 0; j < KOLOM; j++) {
            System.out.print(" " + j + "  ");
        }
        System.out.println();

        System.out.print("  ");
        for (int j = 0; j < KOLOM; j++) {
            System.out.print("+---");
        }
        System.out.println("+");

        for (int i = 0; i < BARIS; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < KOLOM; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");

            System.out.print("  ");
            for (int j = 0; j < KOLOM; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }

        System.out.println();
    }

    public void displayBoardSederhana() {
        System.out.println();
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean guess(int baris, int kolom) {
        if (baris < 0 || baris >= BARIS || kolom < 0 || kolom >= KOLOM) {
            System.out.println("Input tidak valid! Baris harus 0-" + (BARIS-1) +
                    " dan Kolom harus 0-" + (KOLOM-1));
            return true;
        }

        if (revealed[baris][kolom]) {
            System.out.println("Kotak telah dibuka sebelumnya!");
            return true;
        }

        revealed[baris][kolom] = true;

        if (data[baris][kolom] == 1) {
            board[baris][kolom] = 'X';
            gameOver = true;
            win = false;
            System.out.println("BOOM! Anda menemukan bom! Permainan berakhir.");
            return false;
        } else {
            board[baris][kolom] = 'O';
            kotakAmanTerbuka++;
            System.out.println("Kotak Aman");

            if (kotakAmanTerbuka == JUMLAH_AMAN) {
                gameOver = true;
                win = true;
                System.out.println("SELAMAT! Anda berhasil membuka semua kotak aman!");
            }

            return true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWin() {
        return win;
    }

    public void revealBoard() {
        System.out.println("\n=== Papan Lengkap ===");
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                if (data[i][j] == 1) {
                    System.out.print("X ");
                } else if (revealed[i][j]) {
                    System.out.print("O ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayFinalBoard() {
        System.out.println();
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                if (revealed[i][j]) {
                    if (data[i][j] == 1) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getBaris() {
        return BARIS;
    }

    public int getKolom() {
        return KOLOM;
    }

    public int getKotakAmanTerbuka() {
        return kotakAmanTerbuka;
    }

    public int getJumlahAman() {
        return JUMLAH_AMAN;
    }
}