public class LampuJalan extends PerangkatKota implements Konektivitas {

    private boolean lampuMenyala;
    private boolean terhubung;

    // [Syarat 5] super(...) pada konstruktor subclass
    public LampuJalan(String idPerangkat, String lokasi) {
        super(idPerangkat, lokasi);
        this.lampuMenyala = false;
        this.terhubung = false;
    }

    public LampuJalan(String idPerangkat, String lokasi, int levelBaterai) {
        super(idPerangkat, lokasi, levelBaterai);
        this.lampuMenyala = false;
        this.terhubung = false;
    }

    @Override
    public void prosesData(double nilai) {
        if (!isAktif()) {
            System.out.println("[LampuJalan] " + getIdPerangkat() + " belum aktif, data diabaikan.");
        } else if (nilai < 30 && getLevelBaterai() > 10) {
            lampuMenyala = true;
            kurangiBaterai(5);
            System.out.println("[LampuJalan] Gelap (" + nilai + "), lampu MENYALA.");
        } else if (getLevelBaterai() <= 10) {
            lampuMenyala = false;
            System.out.println("[LampuJalan] Baterai lemah, lampu tidak dinyalakan.");
        } else {
            lampuMenyala = false;
            System.out.println("[LampuJalan] Terang (" + nilai + "), lampu MATI.");
        }
    }

    // [Syarat 5 & 6] override + super.namaMethod()
    @Override
    public String getStatus() {
        return super.getStatus() + " | lampu=" + (lampuMenyala ? "NYALA" : "MATI");
    }

    // ===== implementasi Konektivitas =====
    @Override
    public void hubungkan() {
        this.terhubung = true;
        System.out.println("[LampuJalan] " + getIdPerangkat() + " terhubung ke jaringan kota.");
    }

    @Override
    public void putuskan() {
        this.terhubung = false;
        System.out.println("[LampuJalan] " + getIdPerangkat() + " terputus dari jaringan.");
    }

    @Override
    public boolean isTerhubung() {
        return terhubung;
    }

    public boolean isLampuMenyala() {
        return lampuMenyala;
    }

    public void setLampuMenyala(boolean lampuMenyala) {
        this.lampuMenyala = lampuMenyala;
    }
}
