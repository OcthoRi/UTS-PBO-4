public class Kendaraan {
    private String jenis;
    private double durasi;

    private static final int TARIF_MOTOR = 2000;
    private static final int TARIF_MOBIL = 5000;
    private static final int TARIF_TRUK = 10000;
    private static final int BATAS_DISKON = 5;
    private static final double PERSEN_DISKON = 0.10;

    public Kendaraan(String jenis) {
        this.jenis = jenis;
        this.durasi = 0;
    }

    public double hitungBiayaParkir(double durasi) {
        this.durasi = durasi;
        double tarifPerJam = getTarifPerJam();
        double biaya = tarifPerJam * durasi;

        if (durasi > BATAS_DISKON) {
            double diskon = biaya * PERSEN_DISKON;
            biaya -= diskon;
        }

        return biaya;
    }

    public double hitungBiayaParkir(int jamMasuk, int jamKeluar) {
        int durasiJam = jamKeluar - jamMasuk;
        if (durasiJam < 0) {
            durasiJam += 24;
        }
        this.durasi = durasiJam;

        double tarifPerJam = getTarifPerJam();
        double biaya = tarifPerJam * durasiJam;

        if (durasiJam > BATAS_DISKON) {
            double diskon = biaya * PERSEN_DISKON;
            biaya -= diskon;
        }

        return biaya;
    }

    private int getTarifPerJam() {
        if (jenis.equalsIgnoreCase("Motor")) {
            return TARIF_MOTOR;
        } else if (jenis.equalsIgnoreCase("Mobil")) {
            return TARIF_MOBIL;
        } else if (jenis.equalsIgnoreCase("Truk")) {
            return TARIF_TRUK;
        } else {
            return 0;
        }
    }

    public void tampilkanRingkasan(double biaya) {
        System.out.println("\n--- PARKING SUMMARY ---");
        System.out.println("Vehicle Type    : " + jenis);
        System.out.println("Parking Time    : " + (int)durasi + " hour(s)");
        System.out.println("Total Fee       : Rp" + String.format("%.0f", biaya));
    }

    public String getJenis() {
        return jenis;
    }

    public double getDurasi() {
        return durasi;
    }
}