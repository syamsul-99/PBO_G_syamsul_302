package perpus;

public class fiksi extends buku {
    public fiksi (String judul,String penulis){
        super(judul,penulis);
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku Fiksi: " + judul + " oleh " + penulis + " (Genre: misteri)");
    }
}
