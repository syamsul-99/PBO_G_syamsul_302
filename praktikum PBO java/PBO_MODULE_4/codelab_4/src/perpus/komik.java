package perpus;

public class komik extends buku {
    public komik (String judul,String penulis){
        super(judul,penulis);
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku komik: " + judul + " oleh " + penulis + " (Genre:drama)");
    }
}

