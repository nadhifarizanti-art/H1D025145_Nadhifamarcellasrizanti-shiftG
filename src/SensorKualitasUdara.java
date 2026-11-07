public class SensorKualitasUdara extends PerangkatKota implements Konektivitas, Monitoring {

    private double indeksPolusi;
    private String statusUdara;
    private boolean terhubung;

    public SensorKualitasUdara(String idPerangkat, String lokasi) {
        super(idPerangkat, lokasi);
        this.indeksPolusi = 0;
        this.statusUdara = "BAIK";
        this.terhubung = false;
    }

    public SensorKualitasUdara(String idPerangkat, String lokasi, int levelBaterai) {
        super(idPerangkat, lokasi, levelBaterai);
        this.indeksPolusi = 0;
        this.statusUdara = "BAIK";
        this.terhubung = false;
    }

    @Override
    public void prosesData(double nilai) {
        if (!isAktif()) {
            System.out.println("[SensorUdara] " + getIdPerangkat() + " belum aktif, data diabaikan.");
            return;
        }
        this.indeksPolusi = nilai;
        if (nilai > 150) {
            statusUdara = "BERBAHAYA";
            tambahNotifikasi();
            kurangiBaterai(10);
            System.out.println("[SensorUdara] Polusi " + nilai + " -> BERBAHAYA, notifikasi dikirim.");
        } else if (nilai > 100) {
            statusUdara = "SEDANG";
            kurangiBaterai(5);
            System.out.println("[SensorUdara] Polusi " + nilai + " -> SEDANG.");
        } else {
            statusUdara = "BAIK";
            kurangiBaterai(2);
            System.out.println("[SensorUdara] Polusi " + nilai + " -> BAIK.");
        }
    }

    // [Syarat 5 & 6]
    @Override
    public String getStatus() {
        return super.getStatus() + " | udara=" + statusUdara + " (indeks " + indeksPolusi + ")";
    }

    // ===== implementasi Konektivitas =====
    @Override
    public void hubungkan() {
        this.terhubung = true;
        System.out.println("[SensorUdara] " + getIdPerangkat() + " terhubung ke jaringan kota.");
    }

    @Override
    public void putuskan() {
        this.terhubung = false;
        System.out.println("[SensorUdara] " + getIdPerangkat() + " terputus dari jaringan.");
    }

    @Override
    public boolean isTerhubung() {
        return terhubung;
    }

    // ===== implementasi Monitoring =====
    @Override
    public void kirimLaporan() {
        if (terhubung) {
            System.out.println("[SensorUdara] Laporan terkirim: udara " + statusUdara
                    + ", indeks " + indeksPolusi);
        } else {
            System.out.println("[SensorUdara] Gagal kirim laporan: perangkat belum terhubung.");
        }
    }

    public double getIndeksPolusi() {
        return indeksPolusi;
    }

    public void setIndeksPolusi(double indeksPolusi) {
        this.indeksPolusi = indeksPolusi;
    }

    public String getStatusUdara() {
        return statusUdara;
    }

    public void setStatusUdara(String statusUdara) {
        this.statusUdara = statusUdara;
    }
}
