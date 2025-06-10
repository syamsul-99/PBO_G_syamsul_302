package com.praktikum.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPane loginPane = new LoginPane(primaryStage);
        Scene scene = new Scene(loginPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Sistem Lost & Found");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
