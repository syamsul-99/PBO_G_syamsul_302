package com.praktikum.gui;

import com.praktikum.users.Admin;
import com.praktikum.barang.Item;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections   .FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;


public class AdminDashboard extends VBox {
    private final Stage stage;
    private final Admin admin;

    private final ObservableList<Item> barangList = FXCollections.observableArrayList();
    private final ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();

    public AdminDashboard(Stage stage, Admin admin) {
        this.stage = stage;
        this.admin = admin;

        setPadding(new Insets(15));
        setSpacing(15);

        Label label = new Label("Halo, Administrator: " + admin.getNama());

        TableView<Item> barangTable = createBarangTable();
        TableView<Mahasiswa> mahasiswaTable = createMahasiswaTable();

        Button tandaiBtn = new Button("Tandai Claimed");
        tandaiBtn.setOnAction(e -> {
            Item selected = barangTable.getSelectionModel().getSelectedItem();
            if (selected != null && selected.getStatus().equalsIgnoreCase("Reported")) {
                selected.setStatus("Claimed");
                barangList.setAll(LoginPane.reportedItems);
            }
        });

        Button tambahMahasiswaBtn = new Button("Tambah Mahasiswa");
        tambahMahasiswaBtn.setOnAction(e -> tambahMahasiswa());

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            stage.setScene(new Scene(new LoginPane(stage), 400, 300));
        });

        getChildren().addAll(label, new Label("Data Barang:"), barangTable, tandaiBtn,
                new Label("Data Mahasiswa:"), mahasiswaTable, tambahMahasiswaBtn, logoutBtn);
    }

    private static ObservableValue<String> call(TableColumn.CellDataFeatures<Mahasiswa, String> data) {
        return new SimpleStringProperty(data.getValue().getNama());
    }


    private TableView<Item> createBarangTable() {
        barangList.setAll(LoginPane.reportedItems);

        TableView<Item> table = new TableView<>(barangList);
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getItemName()));

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));


        table.getColumns().addAll(namaCol, lokasiCol, statusCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return table;
    }

    private TableView<Mahasiswa> createMahasiswaTable() {
        for (User u : LoginPane.userList) {
            if (u instanceof Mahasiswa m) mahasiswaList.add(m);
        }

        TableView<Mahasiswa> table = new TableView<>(mahasiswaList);

        TableColumn<Mahasiswa, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(AdminDashboard::call);

        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNim()));


        table.getColumns().addAll(namaCol, nimCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return table;
    }

    private void tambahMahasiswa() {
        Dialog<Mahasiswa> dialog = new Dialog<>();
        dialog.setTitle("Tambah Mahasiswa");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField namaField = new TextField();
        TextField nimField = new TextField();

        grid.add(new Label("Nama:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("NIM:"), 0, 1);
        grid.add(nimField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        ButtonType simpanBtn = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(simpanBtn, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == simpanBtn) {
                return new Mahasiswa(namaField.getText(), nimField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(mhs -> {
            LoginPane.userList.add(mhs);
            mahasiswaList.add(mhs);
        });
    }
}
