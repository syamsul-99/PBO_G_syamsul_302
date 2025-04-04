// Superclass karakter game
class KarakterGame {
    private String nama;
    private int kesehatan;

    public KarakterGame(String nama, int kesehatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public void serang(KarakterGame target) {
        // Akan di-override oleh subclass
    }
}

// Subclass Pahlawan (brimstone)
class Pahlawan extends KarakterGame {
    public Pahlawan(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan rudal balistik!");
        target.setKesehatan(target.getKesehatan() - 20);
        System.out.println("Kesehatan " + target.getNama() + " sekarang: " + target.getKesehatan());
    }
}

// Subclass Musuh (viper)
class Musuh extends KarakterGame {
    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan bisah ular depok !");
        target.setKesehatan(target.getKesehatan() - 15);
        System.out.println("Kesehatan " + target.getNama() + " sekarang: " + target.getKesehatan());
    }
}

// Kelas Utama Main
public class Main {
    public static void main(String[] args) {
        KarakterGame karakterUmum = new KarakterGame("Karakter Umum", 100);
        Pahlawan brimstone = new Pahlawan("Brimstone", 200);
        Musuh viper = new Musuh("Viper", 200);

        // Menampilkan status awal
        System.out.println("Kesehatan awal brimstone: " + brimstone.getKesehatan());
        System.out.println("Kesehatan awal viper: " + viper.getKesehatan());
        System.out.println();

        // Simulasi pertarungan
        brimstone.serang(viper); // Brimstone menyerang Viper
        System.out.println();
        viper.serang(brimstone); // Viper menyerang Brimstone
    }
}
