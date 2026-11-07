public class Main {

    public static void main(String[] args) {

        // ===== [Syarat 3 & 4] Konstruktor overloading =====
        header("Syarat 3: Overloaded constructor");
        LampuJalan lampu1 = new LampuJalan("LJ-01", "Jl. Jenderal Soedirman");        // konstruktor 1
        LampuJalan lampu2 = new LampuJalan("LJ-02", "Jl. Gatot Subroto", 8);          // konstruktor 2
        SensorParkir parkir = new SensorParkir("SP-01", "Alun-Alun", 3);
        SensorKualitasUdara udara = new SensorKualitasUdara("SU-01", "Terminal");
        System.out.println("Baterai LJ-01 (konstruktor 1): " + lampu1.getLevelBaterai());
        System.out.println("Baterai LJ-02 (konstruktor 2): " + lampu2.getLevelBaterai());

        // ===== [Syarat 1] State dan behavior =====
        header("Syarat 1: State & behavior (void + return value)");
        lampu1.aktifkan();                              // method void
        lampu2.aktifkan();
        parkir.aktifkan();
        udara.aktifkan();
        System.out.println(lampu1.getStatus());         // method fungsi (return String)

        // ===== [Syarat 2] Immutability =====
        header("Syarat 2: Immutability");
        System.out.println("Konstanta BATERAI_MAKS = " + PerangkatKota.BATERAI_MAKS);
        System.out.println("idPerangkat (final, tanpa setter) = " + lampu1.getIdPerangkat());
        // lampu1.setIdPerangkat("X");   // <- tidak bisa, method-nya memang tidak ada

        // ===== [Syarat 8] Logika if/else yang membaca & mengubah state =====
        header("Syarat 8: Logika if/else pada prosesData()");
        lampu1.prosesData(20);     // gelap -> menyala, baterai turun
        lampu1.prosesData(80);     // terang -> mati
        lampu2.prosesData(10);     // baterai lemah -> tidak menyala
        parkir.prosesData(2);
        parkir.prosesData(3);      // penuh
        udara.prosesData(60);
        udara.prosesData(180);     // berbahaya

        // ===== [Syarat 6 & polimorfisme superclass] Overriding =====
        header("Syarat 4, 5, 6: Inheritance, super chaining, overriding");
        PerangkatKota[] semua = { lampu1, lampu2, parkir, udara };
        for (PerangkatKota p : semua) {
            System.out.println(p.getStatus());   // getStatus() versi subclass (override + super.getStatus())
        }

        // ===== [Syarat 9] Polimorfisme lewat interface =====
        header("Syarat 9: Polimorfisme interface");
        Konektivitas k1 = new LampuJalan("LJ-03", "Jl. Ahmad Yani");
        Konektivitas k2 = new SensorParkir("SP-02", "Pasar", 10);
        k1.hubungkan();
        k2.hubungkan();
        System.out.println("k1 terhubung? " + k1.isTerhubung());
        k2.putuskan();

        // ===== [Syarat 10] Multiple interface =====
        header("Syarat 10: Satu class, dua interface");
        SensorKualitasUdara sensorUdara = new SensorKualitasUdara("SU-02", "Kampus");
        Konektivitas kon = sensorUdara;     // dilihat sebagai Konektivitas
        Monitoring mon = sensorUdara;       // dilihat sebagai Monitoring
        sensorUdara.aktifkan();
        sensorUdara.prosesData(170);
        mon.kirimLaporan();                 // belum terhubung -> gagal
        kon.hubungkan();
        mon.kirimLaporan();                 // sudah terhubung -> berhasil
        System.out.println(sensorUdara.getStatus());
    }

    private static void header(String judul) {
        System.out.println();
        System.out.println("=== " + judul + " ===");
    }
}
