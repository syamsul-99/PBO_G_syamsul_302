import java.util.Scanner;

class RekeningBank {
    // Atribut
    String nomorRekening;
    String namaPemilik;
    double saldo;

    // Konstruktor
    public RekeningBank(String nomorRekening, String namaPemilik, double saldo) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
    }

    // Metode untuk menampilkan informasi rekening
    public void tampilkanInfo() {
        System.out.println("-----------------------");
        System.out.println("Nomor Rekening: " + nomorRekening);
        System.out.println("Nama Pemilik: " + namaPemilik);
        System.out.println("Saldo: Rp" + saldo);
        System.out.println("-----------------------");
    }

    // Metode untuk menyetor uang
    public void setorUang(double jumlah) {
        saldo += jumlah;
        System.out.println(namaPemilik + " menyetor Rp" + jumlah + ". Saldo sekarang: Rp" + saldo);
    }

    // Metode untuk menarik uang
    public void tarikUang(double jumlah) {
        if (saldo >= jumlah) {
            saldo -= jumlah;
            System.out.println(namaPemilik + " menarik Rp" + jumlah + ". (Berhasil) Saldo sekarang: Rp" + saldo);
        } else {
            System.out.println(namaPemilik + " menarik Rp" + jumlah + ". (Gagal, Saldo tidak mencukupi) Saldo saat ini: Rp" + saldo);
        }
    }

    // Metode untuk transfer uang
    public void transfer(RekeningBank penerima, double jumlah) {
        if (saldo >= jumlah) {
            saldo -= jumlah;
            penerima.saldo += jumlah;
            System.out.println(namaPemilik + " mentransfer Rp" + jumlah + " ke " + penerima.namaPemilik);
        } else {
            System.out.println(namaPemilik + " gagal mentransfer Rp" + jumlah + " ke " + penerima.namaPemilik + " (Saldo tidak mencukupi)");
        }
    }
}

public class bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat objek rekening1 dan rekening2
        RekeningBank rekening1 = new RekeningBank("202410370311302", "syamsul", 500000);
        RekeningBank rekening2 = new RekeningBank("202410370311305", "ibra", 100000);

        // Menampilkan informasi awal rekening
        rekening1.tampilkanInfo();
        rekening2.tampilkanInfo();

        // Input transaksi dari pengguna
        System.out.print("Masukkan jumlah setoran untuk " + rekening1.namaPemilik + ": ");
        double setoran1 = scanner.nextDouble();
        rekening1.setorUang(setoran1);

        System.out.print("Masukkan jumlah setoran untuk " + rekening2.namaPemilik + ": ");
        double setoran2 = scanner.nextDouble();
        rekening2.setorUang(setoran2);

        System.out.print("Masukkan jumlah penarikan untuk " + rekening1.namaPemilik + ": ");
        double penarikan1 = scanner.nextDouble();
        rekening1.tarikUang(penarikan1);

        System.out.print("Masukkan jumlah penarikan untuk " + rekening2.namaPemilik + ": ");
        double penarikan2 = scanner.nextDouble();
        rekening2.tarikUang(penarikan2);

        System.out.print("Masukkan jumlah transfer dari " + rekening1.namaPemilik + " ke " + rekening2.namaPemilik + ": ");
        double transfer1 = scanner.nextDouble();
        rekening1.transfer(rekening2, transfer1);

        System.out.print("Masukkan jumlah transfer dari " + rekening2.namaPemilik + " ke " + rekening1.namaPemilik + ": ");
        double transfer2 = scanner.nextDouble();
        rekening2.transfer(rekening1, transfer2);

        // Menampilkan informasi akhir rekening
        rekening1.tampilkanInfo();
        rekening2.tampilkanInfo();

        scanner.close();
    }
}
