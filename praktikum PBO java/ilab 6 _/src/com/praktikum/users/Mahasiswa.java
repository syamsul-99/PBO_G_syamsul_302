package com.praktikum.users;

import com.praktikum.actions.mahasiswaaction;
import com.praktikum.barang.Item;
import com.praktikum.main.LoginSystem;

import java.util.Scanner;

public class Mahasiswa extends User implements mahasiswaaction {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return inputNama.equalsIgnoreCase(getNama()) && inputNim.equals(getNim());
    }

    @Override
    public void displayInfo() {
        System.out.println("Login berhasil sebagai Mahasiswa.");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM : " + getNim());
    }

    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n== Laporkan Barang ==");

        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Lokasi: ");
        String lokasi = scanner.nextLine();

        Item item = new Item(nama, deskripsi, lokasi, "Reported");
        LoginSystem.reportedItems.add(item);
        System.out.println(">> Barang berhasil dilaporkan.");
    }

    @Override
    public void viewReportedItems() {
        System.out.println("\n== Daftar Laporan Barang ==");
        boolean ada = false;
        for (Item item : LoginSystem.reportedItems) {
            if (item.getStatus().equalsIgnoreCase("Reported")) {
                System.out.println("- " + item.getItemName() + " | " + item.getDescription() + " | " + item.getLocation());
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Belum ada laporan barang.");
        }
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;
        do {
            System.out.println("\n== MENU MAHASISWA ==");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                continue;
            }

            switch (pilihan) {
                case 1 -> reportItem();
                case 2 -> viewReportedItems();
                case 0 -> System.out.println("Logout berhasil.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }
}
