import java.util.Scanner;
public class login {
  public static void main(String[]agrs){
    Scanner Scanner = new Scanner(System.in);

      System.out.println("Pilih login:");
      System.out.println("1. Admin");
      System.out.println("2. Mahasiswa");
      System.out.print("Masukkan pilihan: ");
      int pilihan = Scanner.nextInt();
      Scanner.nextLine(); // Membersihkan newline

      if (pilihan == 1) {
          // Login Admin
          System.out.print("Masukkan username: ");
          String username = Scanner.nextLine();
          System.out.print("Masukkan password: ");
          String password = Scanner.nextLine();

          // Validasi username dan password
          String usernameValid = "Admin302";
          String passwordValid = "Password302";

          if (username.equals(usernameValid) && password.equals(passwordValid)) {
              System.out.println("Login Admin berhasil!");
          } else {
              System.out.println("Login gagal! Username atau password salah.");
          }
      } else if (pilihan == 2) {
          // Login Mahasiswa
          System.out.print("Masukkan nama: ");
          String nama = Scanner.nextLine();
          System.out.print("Masukkan NIM: ");
          String nim = Scanner.nextLine();

          // Validasi nama dan NIM
          String namaValid = "Syamsul falah imran";
          String nimValid = "202410370110302";

          if (nama.equals(namaValid) && nim.equals(nimValid)) {
              System.out.println("Login Mahasiswa berhasil!");
              System.out.println("Nama: " + nama);
              System.out.println("NIM: " + nim);
          } else {
              System.out.println("Login gagal! Nama atau NIM salah.");
          }
      } else {
          // Pilihan tidak valid
          System.out.println("Pilihan tidak valid.");
      }

      Scanner.close();

 }
}
