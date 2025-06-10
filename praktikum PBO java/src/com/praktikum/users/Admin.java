package com.praktikum.users;

import com.praktikum.actions.adminActions;
import com.praktikum.barang.Item;
import com.praktikum.main.LoginSystem;

import java.util.*;

public class Admin extends User implements adminActions {
    private String username;
    private String password;
    private Scanner scanner = new Scanner(System.in);

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login berhasil sebagai Admin.");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM : " + getNim());
        System.out.println("Username: " + username);
    }

    @Override
    public void manageItems() {
        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n== Kelola Barang ==");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1 -> {
                        if (LoginSystem.reportedItems.isEmpty()) {
                            System.out.println("Belum ada laporan barang.");
                        } else {
                            for (Item item : LoginSystem.reportedItems) {
                                System.out.println(item.getItemName() + " | " + item.getDescription() + " | " +
                                        item.getLocation() + " | Status: " + item.getStatus());
                            }
                        }
                    }
                    case 2 -> {
                        List<Item> belumDiambil = new ArrayList<>();
                        for (Item item : LoginSystem.reportedItems)
                            if (item.getStatus().equalsIgnoreCase("Reported")) belumDiambil.add(item);

                        if (belumDiambil.isEmpty()) {
                            System.out.println("Tidak ada barang yang belum diambil.");
                            break;
                        }

                        for (int i = 0; i < belumDiambil.size(); i++) {
                            Item item = belumDiambil.get(i);
                            System.out.println((i + 1) + ". " + item.getItemName() + " - " + item.getDescription());
                        }

                        System.out.print("Masukkan nomor barang: ");
                        try {
                            int idx = Integer.parseInt(scanner.nextLine()) - 1;
                            Item selected = belumDiambil.get(idx);
                            selected.setStatus("Claimed");
                            System.out.println("Barang berhasil ditandai sebagai 'Claimed'.");
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println("Indeks tidak valid.");
                        }
                    }
                    case 0 -> System.out.println("Kembali ke menu utama.");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    @Override
    public void manageUsers() {
        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n== Kelola Mahasiswa ==");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1 -> {
                        System.out.print("Nama Mahasiswa: ");
                        String nama = scanner.nextLine();
                        System.out.print("NIM : ");
                        String nim = scanner.nextLine();
                        Mahasiswa m = new Mahasiswa(nama, nim);
                        LoginSystem.userList.add(m);
                        System.out.println("Mahasiswa berhasil ditambahkan.");
                    }
                    case 2 -> {
                        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
                        String targetNIM = scanner.nextLine();
                        boolean ditemukan = false;

                        Iterator<User> it = LoginSystem.userList.iterator();
                        while (it.hasNext()) {
                            User u = it.next();
                            if (u instanceof Mahasiswa && u.getNim().equals(targetNIM)) {
                                it.remove();
                                ditemukan = true;
                                System.out.println("Mahasiswa berhasil dihapus.");
                                break;
                            }
                        }

                        if (!ditemukan) System.out.println("Mahasiswa tidak ditemukan.");
                    }
                    case 0 -> System.out.println("Kembali ke menu utama.");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
            }
        }
    }

    @Override
    public void displayAppMenu() {
        int pilihan;
        do {
            System.out.println("\n== MENU ADMIN ==");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1 -> manageItems();
                    case 2 -> manageUsers();
                    case 0 -> System.out.println("Logout berhasil.");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                pilihan = -1;
            }
        } while (pilihan != 0);
    }
}
