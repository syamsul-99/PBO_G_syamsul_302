package com.praktikum.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.praktikum.users.*;
import com.praktikum.barang.Item;
import java.util.ArrayList;
import java.util.List;

public class LoginPane extends VBox {

    public static List<Item> reportedItems = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();

    public LoginPane(Stage stage) {
        setPadding(new Insets(20));
        setSpacing(10);

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Mahasiswa", "Admin");
        roleBox.setValue("Mahasiswa");

        TextField userField = new TextField();
        userField.setPromptText("Nama / Username");

        PasswordField passField = new PasswordField();
        passField.setPromptText("NIM / Password");

        Button loginBtn = new Button("Login");

        Label statusLabel = new Label();

        getChildren().addAll(new Label("Login Sistem Lost & Found"), roleBox, userField, passField, loginBtn, statusLabel);

        // Data default
        if (userList.isEmpty()) {
            userList.add(new Admin("Admin", "0000", "admin", "admin123"));
            userList.add(new Mahasiswa("syamsul", "70110302"));
            userList.add(new Mahasiswa("jaki", "297"));

            reportedItems.add(new Item("Dompet", "Dompet kulit warna coklat", "Kantin", "Reported"));
            reportedItems.add(new Item("pesawat", "boing777", "meja lab 1", "Reported"));
        }

        loginBtn.setOnAction(e -> {
            String nama = userField.getText();
            String pass = passField.getText();
            String role = roleBox.getValue();

            User user = null;

            if (role.equals("Mahasiswa")) {
                for (User u : userList) {
                    if (u instanceof Mahasiswa m && m.login(nama, pass)) {
                        user = m;
                        stage.setScene(new Scene(new MahasiswaDashboard(stage, (Mahasiswa) m), 900, 400));
                        break;
                    }
                }
                if (user == null) statusLabel.setText("Login Mahasiswa gagal.");
            } else {
                for (User u : userList) {
                    if (u instanceof Admin a && a.login(nama, pass)) {
                        user = a;
                        stage.setScene(new Scene(new AdminDashboard(stage, (Admin) a), 900, 500));
                        break;
                    }
                }
                if (user == null) statusLabel.setText("Login Admin gagal.");
            }
        });
    }
}
