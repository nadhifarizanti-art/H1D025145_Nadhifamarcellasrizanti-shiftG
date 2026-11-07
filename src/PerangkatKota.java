public abstract class PerangkatKota {

    // [Syarat 2] konstanta global
    public static final int BATERAI_MAKS = 100;

    // [Syarat 2] atribut instans kekal (tidak punya setter)
    private final String idPerangkat;

    // [Syarat 1 & 3] atribut state, semuanya private
    private String lokasi;
    private boolean aktif;
    private int levelBaterai;
    private int jumlahNotifikasi;

    // [Syarat 3] konstruktor overloading #1, memakai this(...)
    public PerangkatKota(String idPerangkat, String lokasi) {
        this(idPerangkat, lokasi, BATERAI_MAKS);
    }

    // [Syarat 3] konstruktor overloading #2
    public PerangkatKota(String idPerangkat, String lokasi, int levelBaterai) {
        this.idPerangkat = idPerangkat;
        this.lokasi = lokasi;
        this.aktif = false;
        this.jumlahNotifikasi = 0;
        setLevelBaterai(levelBaterai);
    }

    // [Syarat 1] method prosedur (void)
    public void aktifkan() {
        this.aktif = true;
    }

    public void nonaktifkan() {
        this.aktif = false;
    }

    public void kurangiBaterai(int jumlah) {
        setLevelBaterai(this.levelBaterai - jumlah);
    }

    public void tambahNotifikasi() {
        this.jumlahNotifikasi++;
    }

    // [Syarat 1] method fungsi (mengembalikan nilai)
    public String getStatus() {
        return idPerangkat + " @ " + lokasi
                + " | aktif=" + aktif
                + " | baterai=" + levelBaterai + "%"
                + " | notifikasi=" + jumlahNotifikasi;
    }

    // [Syarat 7] abstract method, diimplementasikan di tiap subclass
    public abstract void prosesData(double nilai);

    // ===== Getter & setter [Syarat 3] =====
    // idPerangkat hanya getter karena final
    public String getIdPerangkat() {
        return idPerangkat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public int getLevelBaterai() {
        return levelBaterai;
    }

    // dijaga agar nilainya selalu antara 0 dan BATERAI_MAKS
    public void setLevelBaterai(int levelBaterai) {
        if (levelBaterai < 0) {
            this.levelBaterai = 0;
        } else if (levelBaterai > BATERAI_MAKS) {
            this.levelBaterai = BATERAI_MAKS;
        } else {
            this.levelBaterai = levelBaterai;
        }
    }

    public int getJumlahNotifikasi() {
        return jumlahNotifikasi;
    }

    public void setJumlahNotifikasi(int jumlahNotifikasi) {
        this.jumlahNotifikasi = jumlahNotifikasi;
    }
}
