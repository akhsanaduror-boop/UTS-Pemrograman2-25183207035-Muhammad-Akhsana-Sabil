package kendaraan;
   
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final ArrayList<Kendaraan> daftar = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n===== RENTAL KENDARAAN =====");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Kendaraan");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hitung Biaya");
            System.out.println("5. Detail Kendaraan");
            System.out.println("0. Keluar");
            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1: tambahKendaraan(); break;
                case 2: tampilkanSemua(); break;
                case 3: ubahData(); break;
                case 4: hitungBiaya(); break;
                case 5: detailKendaraan(); break;
                case 0: System.out.println("Terima kasih. Program selesai."); break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    
    private static void tambahKendaraan() {
        System.out.println("\n-- Tambah Kendaraan --");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        int jenis = bacaInt("Pilih jenis kendaraan: ");
        if (jenis != 1 && jenis != 2) {
            System.out.println("Jenis tidak valid.");
            return;
        }

        String nama = bacaString("Nama kendaraan : ");
        String plat = bacaString("Nomor plat     : ");
        double harga = bacaDouble("Harga sewa/hari : ");
        int lama = bacaInt("Lama sewa (hari) : ");

        try {
            if (jenis == 1) {
                int kursi = bacaInt("Jumlah kursi    : ");
                daftar.add(new Mobil(nama, plat, harga, lama, kursi));
            } else {
                String jm = bacaString("Jenis motor (Matic/Manual/Sport): ");
                daftar.add(new Motor(nama, plat, harga, lama, jm));
            }
            System.out.println("Kendaraan berhasil ditambahkan.");
        } catch (IllegalArgumentException e) {
            // Data tidak valid -> objek tidak dibuat
            System.out.println("Gagal menambah: " + e.getMessage());
        }
    }

    
    private static void tampilkanSemua() {
        System.out.println("\n-- Daftar Kendaraan --");
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return;
        }
        System.out.printf("%-3s %-20s %-12s %-7s %-15s%n",
                "No", "Nama", "Plat", "Jenis", "Harga Sewa/Hari");
        for (int i = 0; i < daftar.size(); i++) {
            Kendaraan k = daftar.get(i);
            System.out.printf("%-3d %-20s %-12s %-7s %-15s%n",
                    i + 1, k.getNamaKendaraan(), k.getNomorPlat(),
                    namaJenis(k), Kendaraan.formatRupiah(k.getHargaSewa()));
        }
    }

    
    private static void ubahData() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;

        System.out.println("\nData yang diubah:");
        System.out.println("1. Nama");
        System.out.println("2. Nomor Plat");
        System.out.println("3. Harga Sewa");
        System.out.println("4. Lama Sewa");
        if (k instanceof Mobil) {
            System.out.println("5. Jumlah Kursi");
        } else if (k instanceof Motor) {
            System.out.println("5. Jenis Motor");
        }
        int pilih = bacaInt("Pilih: ");

        try {
            switch (pilih) {
                case 1: k.setNamaKendaraan(bacaString("Nama baru : ")); break;
                case 2: k.setNomorPlat(bacaString("Plat baru : ")); break;
                case 3: k.setHargaSewa(bacaDouble("Harga sewa baru : ")); break;
                case 4: k.setLamaSewa(bacaInt("Lama sewa baru (hari) : ")); break;
                case 5:
                    if (k instanceof Mobil) {
                        ((Mobil) k).setJumlahKursi(bacaInt("Jumlah kursi baru : "));
                    } else if (k instanceof Motor) {
                        ((Motor) k).setJenisMotor(bacaString("Jenis motor baru (Matic/Manual/Sport): "));
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    return;
            }
            System.out.println("Data berhasil diubah.");
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal mengubah: " + e.getMessage());
        }
    }

    
    private static void hitungBiaya() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;
        System.out.println("\n-- Hitung Biaya --");
        System.out.println(k.getNamaKendaraan() + ": "
                + Kendaraan.formatRupiah(k.getHargaSewa()) + " x "
                + k.getLamaSewa() + " hari");
        System.out.println("Total Biaya : " + Kendaraan.formatRupiah(k.hitungBiaya()));
    }

    
    private static void detailKendaraan() {
        Kendaraan k = pilihKendaraan();
        if (k == null) return;
        System.out.println("\n-- Detail Kendaraan --");
        k.tampilkanInfo(); 
    }

    
    private static String namaJenis(Kendaraan k) {
        return (k instanceof Mobil) ? "Mobil" : "Motor";
    }

    
    private static Kendaraan pilihKendaraan() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return null;
        }
        tampilkanSemua();
        int no = bacaInt("Pilih nomor urut kendaraan: ");
        if (no < 1 || no > daftar.size()) {
            System.out.println("Nomor tidak valid.");
            return null;
        }
        return daftar.get(no - 1);
    }

    private static String bacaString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    private static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka bulat yang valid.");
            }
        }
    }

    private static double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }
}
