public class Karyawan {
    private String id;
    private String nama;
    private String posisi;
    private double gaji;

    public Karyawan(String id, String nama, String posisi, double gaji) {
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
        if (gaji < 0) {
            this.gaji = 0;
            System.out.println("Peringatan: Gaji tidak boleh negatif. Ditetapkan menjadi 0.");
        } else {
            this.gaji = gaji;
        }
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public double getGaji() {
        return gaji;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setGaji(double gaji) {
        if (gaji < 0) {
            System.out.println("Error: Gaji tidak boleh negatif. Perubahan dibatalkan.");
        } else {
            this.gaji = gaji;
            System.out.println("Gaji berhasil diubah menjadi: Rp " + formatGaji());
        }
    }

    public void tampilkanInfo() {
        System.out.println("=====================================");
        System.out.println("ID      : " + id);
        System.out.println("Nama    : " + nama);
        System.out.println("Posisi  : " + posisi);
        System.out.println("Gaji    : Rp " + formatGaji());
        System.out.println("=====================================");
    }

    private String formatGaji() {
        return String.format("%,.2f", gaji);
    }
}