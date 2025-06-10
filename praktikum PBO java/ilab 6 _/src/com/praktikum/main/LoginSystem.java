package com.praktikum.main;

import com.praktikum.barang.Item;
import com.praktikum.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {
    public static List<Item> reportedItems = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tambah admin dan mahasiswa default
       Admin defaultAdmin = new Admin("Admin", "0000", "admin", "admin123");
        Mahasiswa mhs1 = new Mahasiswa("syamsul", "70110302");
        Mahasiswa mhs2 = new Mahasiswa("jaki","297");
        userList.add(defaultAdmin);
        userList.add(mhs1);
        userList.add(mhs2);

        reportedItems.add(new Item("Dompet", "Dompet kulit warna coklat", "Kantin", "Reported"));
        reportedItems.add(new Item("pesawat","boing777","meja lab 1","Reported"));

        while (true) {
            System.out.println("\n=== Sistem Login ===");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi (1/2/0): ");
            String pilihan = scanner.nextLine();

            User user = null;

            switch (pilihan) {
                case "1" -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    for (User u : userList) {
                        if (u instanceof Admin a && a.login(username, password)) {
                            user = a;
                            break;
                        }
                    }
                    if (user != null) {
                        user.displayInfo();
                        user.displayAppMenu();
                    } else {
                        System.out.println("Login Admin gagal.");
                    }
                }
                case "2" -> {
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("NIM : ");
                    String nim = scanner.nextLine();
                    for (User u : userList) {
                        if (u instanceof Mahasiswa m && m.login(nama, nim)) {
                            user = m;
                            break;
                        }
                    }
                    if (user != null) {
                        user.displayInfo();
                        user.displayAppMenu();
                    } else {
                        System.out.println("Login Mahasiswa gagal.");
                    }
                }
                case "0" -> {
                    System.out.println("Terima kasih. Program selesai.");
                    return; // keluar dari program
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
