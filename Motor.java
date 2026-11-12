package kendaraan;
   
public class Motor extends Kendaraan {
    private String jenisMotor; 

    public Motor(String namaKendaraan, String nomorPlat, double hargaSewa,
                 int lamaSewa, String jenisMotor) {
        super(namaKendaraan, nomorPlat, hargaSewa, lamaSewa);
        setJenisMotor(jenisMotor);
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenisMotor) {
        if (jenisMotor != null) {
            String j = jenisMotor.trim();
            if (j.equalsIgnoreCase("Matic")) {
                this.jenisMotor = "Matic";
                return;
            } else if (j.equalsIgnoreCase("Manual")) {
                this.jenisMotor = "Manual";
                return;
            } else if (j.equalsIgnoreCase("Sport")) {
                this.jenisMotor = "Sport";
                return;
            }
        }
        throw new IllegalArgumentException("Jenis motor harus Matic, Manual, atau Sport.");
    }

    
    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis Kendaraan  : Motor");
        super.tampilkanInfo();
        System.out.println("Jenis Motor      : " + jenisMotor);
        System.out.println("Total Biaya      : " + formatRupiah(hitungBiaya()));
    }
}
