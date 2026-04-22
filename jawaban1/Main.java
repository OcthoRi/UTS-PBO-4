import java.util.Scanner;

public class Main {
    private static Perusahaan perusahaan = new Perusahaan();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   SISTEM MANAJEMEN DATA KARYAWAN");
        System.out.println("=========================================");

        boolean running = true;

        while (running) {
            tampilkanMenu();
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1:
                    tambahKaryawan();
                    break;
                case 2:
                    hapusKaryawan();
                    break;
                case 3:
                    ubahPosisi();
                    break;
                case 4:
                    ubahGaji();
                    break;
                case 5:
                    cariKaryawan();
                    break;
                case 6:
                    perusahaan.tampilkanSemuaKaryawan();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nTerima kasih telah menggunakan program ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("1. Tambah Karyawan");
        System.out.println("2. Hapus Karyawan");
        System.out.println("3. Ubah Posisi Karyawan");
        System.out.println("4. Ubah Gaji Karyawan");
        System.out.println("5. Cari Karyawan (berdasarkan ID)");
        System.out.println("6. Tampilkan Semua Karyawan");
        System.out.println("0. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    private static int bacaPilihan() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void tambahKaryawan() {
        System.out.println("\n--- TAMBAH KARYAWAN BARU ---");

        System.out.print("Masukkan ID Karyawan: ");
        String id = scanner.nextLine();

        if (perusahaan.cariKaryawanById(id) != null) {
            System.out.println("Error: ID '" + id + "' sudah terdaftar!");
            return;
        }

        System.out.print("Masukkan Nama Karyawan: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Posisi/Jabatan: ");
        String posisi = scanner.nextLine();

        System.out.print("Masukkan Gaji: Rp ");
        double gaji = 0;
        try {
            gaji = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Format gaji tidak valid!");
            return;
        }

        Karyawan karyawanBaru = new Karyawan(id, nama, posisi, gaji);
        perusahaan.tambahKaryawan(karyawanBaru);
    }

    private static void hapusKaryawan() {
        System.out.println("\n--- HAPUS KARYAWAN ---");
        System.out.print("Masukkan ID Karyawan yang akan dihapus: ");
        String id = scanner.nextLine();

        perusahaan.hapusKaryawan(id);
    }

    private static void ubahPosisi() {
        System.out.println("\n--- UBAH POSISI KARYAWAN ---");
        System.out.print("Masukkan ID Karyawan: ");
        String id = scanner.nextLine();

        Karyawan karyawan = perusahaan.cariKaryawanById(id);
        if (karyawan == null) {
            System.out.println("Error: Karyawan dengan ID '" + id + "' tidak ditemukan!");
            return;
        }

        System.out.println("Karyawan ditemukan: " + karyawan.getNama());
        System.out.println("Posisi saat ini: " + karyawan.getPosisi());
        System.out.print("Masukkan posisi baru: ");
        String posisiBaru = scanner.nextLine();

        perusahaan.ubahPosisi(id, posisiBaru);
    }

    private static void ubahGaji() {
        System.out.println("\n--- UBAH GAJI KARYAWAN ---");
        System.out.print("Masukkan ID Karyawan: ");
        String id = scanner.nextLine();

        Karyawan karyawan = perusahaan.cariKaryawanById(id);
        if (karyawan == null) {
            System.out.println("Error: Karyawan dengan ID '" + id + "' tidak ditemukan!");
            return;
        }

        System.out.println("Karyawan ditemukan: " + karyawan.getNama());
        System.out.println("Gaji saat ini: Rp " + String.format("%,.2f", karyawan.getGaji()));
        System.out.print("Masukkan gaji baru: Rp ");

        double gajiBaru;
        try {
            gajiBaru = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Format gaji tidak valid!");
            return;
        }

        perusahaan.ubahGaji(id, gajiBaru);
    }

    private static void cariKaryawan() {
        System.out.println("\n--- CARI KARYAWAN ---");
        System.out.print("Masukkan ID Karyawan: ");
        String id = scanner.nextLine();

        Karyawan karyawan = perusahaan.cariKaryawanById(id);

        if (karyawan == null) {
            System.out.println("Karyawan dengan ID '" + id + "' tidak ditemukan.");
        } else {
            System.out.println("\nKaryawan ditemukan:");
            karyawan.tampilkanInfo();
        }
    }
}