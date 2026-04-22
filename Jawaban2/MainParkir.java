import java.util.ArrayList;
import java.util.Scanner;

public class MainParkir {
    private static ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("    Welcome to ParkingChan");
        System.out.println("================================");

        boolean lanjut = true;

        while (lanjut) {
            Kendaraan kendaraan = inputKendaraan();

            if (kendaraan != null) {
                daftarKendaraan.add(kendaraan);
            }

            System.out.print("\nAdd another vehicle? (y/n): ");
            String jawaban = scanner.nextLine();
            if (!jawaban.equalsIgnoreCase("y")) {
                lanjut = false;
            }
        }

        tampilkanLaporanAkhir();
        scanner.close();
    }

    private static Kendaraan inputKendaraan() {
        System.out.println("\n--------------------------------");

        String jenis = inputJenisKendaraan();
        Kendaraan kendaraan = new Kendaraan(jenis);

        System.out.print("Enter Duration (Manual/Time): ");
        String caraInput = scanner.nextLine();

        if (caraInput.equalsIgnoreCase("Manual")) {
            inputDurasiManual(kendaraan);
        } else {
            inputDurasiDenganWaktu(kendaraan);
        }

        return kendaraan;
    }

    private static String inputJenisKendaraan() {
        while (true) {
            System.out.print("Enter vehicle type (Motor/Mobil/Truk): ");
            String jenis = scanner.nextLine();

            if (jenis.equalsIgnoreCase("Motor") ||
                    jenis.equalsIgnoreCase("Mobil") ||
                    jenis.equalsIgnoreCase("Truk")) {
                return jenis.substring(0, 1).toUpperCase() + jenis.substring(1).toLowerCase();
            } else {
                System.out.println("Error: Jenis kendaraan tidak valid!");
            }
        }
    }

    private static void inputDurasiManual(Kendaraan kendaraan) {
        while (true) {
            System.out.print("Enter Duration (in hour): ");
            try {
                double durasi = Double.parseDouble(scanner.nextLine());
                if (durasi > 0) {
                    double biaya = kendaraan.hitungBiayaParkir(durasi);
                    kendaraan.tampilkanRingkasan(biaya);
                    break;
                } else {
                    System.out.println("Error: Durasi harus lebih dari 0 jam!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Format durasi tidak valid!");
            }
        }
    }

    private static void inputDurasiDenganWaktu(Kendaraan kendaraan) {
        int jamMasuk = 0;
        int jamKeluar = 0;

        while (true) {
            System.out.print("Enter entry time (0-23): ");
            try {
                jamMasuk = Integer.parseInt(scanner.nextLine());
                if (jamMasuk >= 0 && jamMasuk <= 23) {
                    break;
                } else {
                    System.out.println("Error: Jam harus antara 0-23!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Format jam tidak valid!");
            }
        }

        while (true) {
            System.out.print("Enter exit time (0-23): ");
            try {
                jamKeluar = Integer.parseInt(scanner.nextLine());
                if (jamKeluar >= 0 && jamKeluar <= 23) {
                    break;
                } else {
                    System.out.println("Error: Jam harus antara 0-23!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Format jam tidak valid!");
            }
        }

        double biaya = kendaraan.hitungBiayaParkir(jamMasuk, jamKeluar);
        kendaraan.tampilkanRingkasan(biaya);

        int durasi = jamKeluar - jamMasuk;
        if (durasi < 0) {
            System.out.println("(Info: Parkir melewati tengah malam, durasi: " + (durasi + 24) + " jam)");
        }
    }

    private static void tampilkanLaporanAkhir() {
        System.out.println("\n=================================");
        System.out.println("        FINAL REPORT");
        System.out.println("=================================");

        if (daftarKendaraan.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang parkir.");
            return;
        }

        System.out.println("\nDetail Kendaraan:");
        System.out.println("---------------------------------");

        double totalSemuaBiaya = 0;

        for (int i = 0; i < daftarKendaraan.size(); i++) {
            Kendaraan k = daftarKendaraan.get(i);
            double biaya = k.hitungBiayaParkir(k.getDurasi());
            totalSemuaBiaya += biaya;

            System.out.println((i + 1) + ". " + k.getJenis() +
                    " | " + (int)k.getDurasi() + " jam | Rp" +
                    String.format("%.0f", biaya));
        }

        System.out.println("\n=================================");
        System.out.println("Total Vehicle Final    : " + daftarKendaraan.size());
        System.out.println("Total Parking Fees Final: Rp" + String.format("%.0f", totalSemuaBiaya));
        System.out.println("=================================");
        System.out.println("Thank You......");
    }
}