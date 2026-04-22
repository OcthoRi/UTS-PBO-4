import java.util.ArrayList;
import java.util.List;

public class Perusahaan {
    private List<Karyawan> daftarKaryawan;

    public Perusahaan() {
        daftarKaryawan = new ArrayList<>();
    }

    public boolean tambahKaryawan(Karyawan karyawan) {
        if (cariKaryawanById(karyawan.getId()) != null) {
            System.out.println("Error: ID karyawan '" + karyawan.getId() + "' sudah ada!");
            return false;
        }

        daftarKaryawan.add(karyawan);
        System.out.println("Sukses: Karyawan " + karyawan.getNama() + " berhasil ditambahkan.");
        return true;
    }

    public boolean hapusKaryawan(String id) {
        Karyawan karyawan = cariKaryawanById(id);

        if (karyawan == null) {
            System.out.println("Error: Karyawan dengan ID '" + id + "' tidak ditemukan!");
            return false;
        }

        daftarKaryawan.remove(karyawan);
        System.out.println("Sukses: Karyawan " + karyawan.getNama() + " (ID: " + id + ") berhasil dihapus.");
        return true;
    }

    public boolean ubahPosisi(String id, String posisiBaru) {
        Karyawan karyawan = cariKaryawanById(id);

        if (karyawan == null) {
            System.out.println("Error: Karyawan dengan ID '" + id + "' tidak ditemukan!");
            return false;
        }

        String posisiLama = karyawan.getPosisi();
        karyawan.setPosisi(posisiBaru);
        System.out.println("Sukses: Posisi karyawan " + karyawan.getNama() +
                " berubah dari '" + posisiLama + "' menjadi '" + posisiBaru + "'.");
        return true;
    }

    public boolean ubahGaji(String id, double gajiBaru) {
        Karyawan karyawan = cariKaryawanById(id);

        if (karyawan == null) {
            System.out.println("Error: Karyawan dengan ID '" + id + "' tidak ditemukan!");
            return false;
        }

        double gajiLama = karyawan.getGaji();
        karyawan.setGaji(gajiBaru);

        if (karyawan.getGaji() != gajiLama) {
            System.out.println("Sukses: Gaji karyawan " + karyawan.getNama() +
                    " berubah dari Rp " + formatGaji(gajiLama) +
                    " menjadi Rp " + formatGaji(gajiBaru) + ".");
            return true;
        }
        return false;
    }

    public Karyawan cariKaryawanById(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                return k;
            }
        }
        return null;
    }

    public void tampilkanSemuaKaryawan() {
        if (daftarKaryawan.isEmpty()) {
            System.out.println("\n[INFO] Belum ada karyawan yang terdaftar.");
            return;
        }

        System.out.println("\n========== DAFTAR KARYAWAN ==========");
        System.out.println("Total karyawan: " + daftarKaryawan.size());
        System.out.println("-------------------------------------");

        for (Karyawan k : daftarKaryawan) {
            k.tampilkanInfo();
        }
    }

    public int getJumlahKaryawan() {
        return daftarKaryawan.size();
    }

    private String formatGaji(double gaji) {
        return String.format("%,.2f", gaji);
    }
}