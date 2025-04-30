package perpus;

public class member implements peminjaman {

    private String nama;
    private String idmember;

    public member(String nama, String idmember) {
        this.nama = nama;
        this.idmember = idmember;
    }

    @Override
    public void pinjamBuku(String judul) {
        System.out.println(nama + " meminjam buku berjudul: " + judul);
    }

    public void pinjamBuku(String judul, int durasi) {
        System.out.println(nama + " meminjam buku '" + judul + "' selama " + durasi + " hari.");
    }

    @Override
    public void kembalikanBuku(String judul) {
        System.out.println(nama + " mengembalikan buku berjudul: " + judul );
    }

    public void display() {
        System.out.println("Anggota: " + nama + " (ID: " + idmember + ")");
    }
}
