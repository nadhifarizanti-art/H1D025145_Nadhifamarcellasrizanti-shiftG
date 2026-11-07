# Responsi Akhir Praktikum PBO - Sistem Smart City (IoT)

## Identitas
- Nama : NADHIFA MARCELLA SRI RIZANTI
- NIM : H1D025145
- Shift : G
- Tema (digit terakhir NIM 5) : **IoT** (domain: Smart City)

## Deskripsi Singkat
Sistem simulasi perangkat IoT pada kota pintar. Terdapat superclass abstract
`PerangkatKota` dengan tiga subclass: `LampuJalan`, `SensorParkir`, dan
`SensorKualitasUdara`, serta dua interface: `Konektivitas` dan `Monitoring`.

## Struktur File
```
.
├── PerangkatKota.java        (superclass abstract)
├── Konektivitas.java         (interface)
├── Monitoring.java           (interface)
├── LampuJalan.java           (subclass)
├── SensorParkir.java         (subclass)
├── SensorKualitasUdara.java  (subclass, 2 interface)
├── Main.java
└── screenshots/
```

## Pemetaan Persyaratan
| No | Konsep | Lokasi |
|----|--------|--------|
| 1 | State dan Behavior | `PerangkatKota` (atribut, `aktifkan()`, `getStatus()`) |
| 2 | Immutability | `BATERAI_MAKS`, `idPerangkat` |
| 3 | Enkapsulasi dan Overloading | atribut private + getter/setter, 2 konstruktor |
| 4 | Inheritance | 3 subclass dengan `extends`, `Main` terpisah |
| 5 | Super Chaining | `super(...)` dan `super.getStatus()` |
| 6 | Method Overriding | `@Override` pada `getStatus()` dan `prosesData()` |
| 7 | Abstract Class | `PerangkatKota` dan `prosesData()` |
| 8 | Logika State | if/else di `prosesData()` tiap subclass |
| 9 | Polimorfisme Interface | `Konektivitas k1 = new LampuJalan(...)` di `Main` |
| 10 | Multiple Interface | `SensorKualitasUdara implements Konektivitas, Monitoring` |

## Cara Compile dan Run (Terminal)
Pastikan JDK sudah terpasang (`javac -version`), lalu di folder proyek:

```
javac *.java
java Main
```

## Screenshot Luaran

![smartcity](https://github.com/user-attachments/assets/20ae0bc0-e4a7-4957-a9bd-f8f5a981f101)
