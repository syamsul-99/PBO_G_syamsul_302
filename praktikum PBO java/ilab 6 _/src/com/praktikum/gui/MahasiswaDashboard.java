package com.praktikum.gui;

import com.praktikum.users.Mahasiswa;
import com.praktikum.barang.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MahasiswaDashboard extends VBox {
    private final Mahasiswa mahasiswa;
    private final Stage stage;
    private final ObservableList<Item> laporanList;

    public MahasiswaDashboard(Stage stage, Mahasiswa mahasiswa) {
        this.stage = stage;
        this.mahasiswa = mahasiswa;
        this.laporanList = FXCollections.observableArrayList();

        setPadding(new Insets(15));
        setSpacing(10);

        Label welcome = new Label("Selamat datang, " + mahasiswa.getNama());
        TableView<Item> table = createTable();

        // Tombol Tambah Laporan
        Button tambahBtn = new Button("Laporkan Barang");
        tambahBtn.setOnAction(e -> showLaporForm());

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            stage.setScene(new Scene(new LoginPane(stage), 400, 300));
        });

        HBox buttonBar = new HBox(10, tambahBtn, logoutBtn);

        getChildren().addAll(welcome, table, buttonBar);
    }

    private TableView<Item> createTable() {
        TableView<Item> table = new TableView<>();
        laporanList.setAll(LoginPane.reportedItems.stream()
                .filter(i -> i.getStatus().equalsIgnoreCase("Reported")).toList());
        table.setItems(laporanList);

        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getItemName()));

        TableColumn<Item, String> deskripsiCol = new TableColumn<>("Deskripsi");
        deskripsiCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));

        table.getColumns().addAll(namaCol, deskripsiCol, lokasiCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return table;


}

    private void showLaporForm() {
        Dialog<Item> dialog = new Dialog<>();
        dialog.setTitle("Form Laporan Barang");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField namaField = new TextField();
        TextField deskripsiField = new TextField();
        TextField lokasiField = new TextField();

        grid.add(new Label("Nama Barang:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("Deskripsi:"), 0, 1);
        grid.add(deskripsiField, 1, 1);
        grid.add(new Label("Lokasi:"), 0, 2);
        grid.add(lokasiField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        ButtonType simpanBtn = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(simpanBtn, ButtonType.CANCEL);

        dialog.setResultConverter(dialogBtn -> {
            if (dialogBtn == simpanBtn) {
                return new Item(
                        namaField.getText(),
                        deskripsiField.getText(),
                        lokasiField.getText(),
                        "Reported"
                );
            }
            return null;
        });

        dialog.showAndWait().ifPresent(item -> {
            LoginPane.reportedItems.add(item);
            laporanList.add(item);
        });
    }
}
