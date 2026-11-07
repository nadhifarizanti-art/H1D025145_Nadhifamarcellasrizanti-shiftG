public class SensorParkir extends PerangkatKota implements Konektivitas {

    private final int kapasitas;   // jumlah slot parkir, tidak berubah
    private int terisi;
    private String statusParkir;
    private boolean terhubung;

    public SensorParkir(String idPerangkat, String lokasi, int kapasitas) {
        super(idPerangkat, lokasi);
        this.kapasitas = kapasitas;
        this.terisi = 0;
        this.statusParkir = "TERSEDIA";
        this.terhubung = false;
    }

    public SensorParkir(String idPerangkat, String lokasi, int kapasitas, int levelBaterai) {
        super(idPerangkat, lokasi, levelBaterai);
        this.kapasitas = kapasitas;
        this.terisi = 0;
        this.statusParkir = "TERSEDIA";
        this.terhubung = false;
    }

    @Override
    public void prosesData(double nilai) {
        if (!isAktif()) {
            System.out.println("[SensorParkir] " + getIdPerangkat() + " belum aktif, data diabaikan.");
            return;
        }
        this.terisi = (int) nilai;
        if (terisi >= kapasitas) {
            statusParkir = "PENUH";
            tambahNotifikasi();
            System.out.println("[SensorParkir] Parkir PENUH (" + terisi + "/" + kapasitas + ").");
        } else {
            statusParkir = "TERSEDIA";
            System.out.println("[SensorParkir] Parkir tersedia, sisa " + (kapasitas - terisi) + " slot.");
        }
        kurangiBaterai(2);
    }

    // [Syarat 5 & 6]
    @Override
    public String getStatus() {
        return super.getStatus() + " | parkir=" + statusParkir
                + " (" + terisi + "/" + kapasitas + ")";
    }

    // ===== implementasi Konektivitas =====
    @Override
    public void hubungkan() {
        this.terhubung = true;
        System.out.println("[SensorParkir] " + getIdPerangkat() + " terhubung ke jaringan kota.");
    }

    @Override
    public void putuskan() {
        this.terhubung = false;
        System.out.println("[SensorParkir] " + getIdPerangkat() + " terputus dari jaringan.");
    }

    @Override
    public boolean isTerhubung() {
        return terhubung;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getTerisi() {
        return terisi;
    }

    public void setTerisi(int terisi) {
        this.terisi = terisi;
    }

    public String getStatusParkir() {
        return statusParkir;
    }

    public void setStatusParkir(String statusParkir) {
        this.statusParkir = statusParkir;
    }
}
