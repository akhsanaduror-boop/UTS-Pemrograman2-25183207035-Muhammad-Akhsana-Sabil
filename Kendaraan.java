   package kendaraan;
   
public class Kendaraan {
    private String namaKendaraan;
    private String nomorPlat;
    private double hargaSewa;   
    private int lamaSewa;       

    public Kendaraan(String namaKendaraan, String nomorPlat, double hargaSewa, int lamaSewa) {
        
        setNamaKendaraan(namaKendaraan);
        setNomorPlat(nomorPlat);
        setHargaSewa(hargaSewa);
        setLamaSewa(lamaSewa);
    }

    
    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        if (namaKendaraan == null || namaKendaraan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kendaraan tidak boleh kosong.");
        }
        this.namaKendaraan = namaKendaraan.trim();
    }

    public String getNomorPlat() {
        return nomorPlat;
    }

    public void setNomorPlat(String nomorPlat) {
        if (nomorPlat == null || nomorPlat.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor plat tidak boleh kosong.");
        }
        this.nomorPlat = nomorPlat.trim().toUpperCase();
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        if (hargaSewa <= 0) {
            throw new IllegalArgumentException("Harga sewa harus lebih dari 0.");
        }
        this.hargaSewa = hargaSewa;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(int lamaSewa) {
        if (lamaSewa < 1) {
            throw new IllegalArgumentException("Lama sewa minimal 1 hari.");
        }
        this.lamaSewa = lamaSewa;
    }

    
    public double hitungBiaya() {
        return hargaSewa * lamaSewa;
    }

    
    public static String formatRupiah(double nilai) {
        return "Rp" + String.format("%,.0f", nilai).replace(',', '.');
    }

    
    public void tampilkanInfo() {
        System.out.println("Nama             : " + namaKendaraan);
        System.out.println("Nomor Plat       : " + nomorPlat);
        System.out.println("Harga Sewa/Hari  : " + formatRupiah(hargaSewa));
        System.out.println("Lama Sewa        : " + lamaSewa + " hari");
    }
}
