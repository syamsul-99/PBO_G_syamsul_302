package app;
import perpus.*;
public class Main {
    public static void main(String[] args) {
        buku non_fiksi = new non_fiksi("sejarawan hitler", "orang jerman");
        buku fiksi = new fiksi("Pembunuhan di Nihonbasi ", "Keigo Higashino");
        buku komik = new komik("pacar sewaan","Reiji Miyajima");

        komik.displayInfo();
        non_fiksi.displayInfo();
        fiksi.displayInfo();
        System.out.print(System.lineSeparator());

        member member1 = new member("dzaki", "297");
        member member2 = new member("althof", "306");
        member member3 = new member("syamsul","302");

        member1.display();
        member2.display();
        member3.display();
        System.out.print(System.lineSeparator());

        member1.pinjamBuku("sejarawan hitler");
        member2.pinjamBuku("Pembunuhan di Nihonbasi", 7);
        member3.pinjamBuku("komisan");
        System.out.print(System.lineSeparator());

        member1.kembalikanBuku("pendongeng handal");
        member2.kembalikanBuku("Pembunuhan di Nihonbasi");
        member3.kembalikanBuku("pacar sewaan");
        System.out.print(System.lineSeparator());
    }
}
