package perpus;

public class non_fiksi extends buku {
    public non_fiksi(String judul, String penulis) {
        super(judul, penulis);
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku non-fiksi: " + judul + " oleh " + penulis + " (Bidang: Sejarah & Ilmu Pengetahuan)");
    }
}