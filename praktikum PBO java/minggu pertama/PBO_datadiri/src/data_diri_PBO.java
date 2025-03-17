import java.util.Scanner;
public class data_diri_PBO {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

//meminta input dari pengguna
            System.out.print("Masukkan nama: ");//input data nama
            String nama = scanner.nextLine();//menyimpan data "nama"

            System.out.print("Masukkan jenis kelamin (P/L): ");//menentukan jenis kelamin
            String jenisKelamin = scanner.nextLine();//menyimpan data "jeniskelamin"

            System.out.print("Masukkan tahun lahir: ");//mengimputkan tahun lahir
            int tahunLahir = scanner.nextInt();//menyimpan data angka int "tahun lahir"

            System.out.print("masukan tahun sekarang:");//mengimputkan tahun sekarang
             int tahunSekarang = scanner.nextInt();//menyimpan data angka int "tahun sekarang "

//sistem untuk menghitung umur
            int umur = tahunSekarang - tahunLahir;//"tahun sekarang dikurang tahun lahir "

//untuk menentukan jenis kelamin dengan memakai if else
        String jenisKelaminLengkap;
        if (jenisKelamin.equalsIgnoreCase("L")) { //jika menginput l/L maka akan mencetak laki laki
            jenisKelaminLengkap = "Laki-Laki";
        } else {
            jenisKelaminLengkap = "Perempuan";// jika ada input lain maka akan mengimput perempuan
        }

//hasil yang sudah di input akan kecetak
            System.out.println("\nData Diri:");
            System.out.println("Nama         : " + nama);//mengambil data nama dan ngecetak nama
            System.out.println("Jenis Kelamin: " + jenisKelaminLengkap);//mengambil data jeniskelamin dan ngecetak jenis kelamin
            System.out.println("Umur         : " + umur + " tahun");//ngambil data umur dan ngecetak umr jogja

            scanner.close();
        }
    }


