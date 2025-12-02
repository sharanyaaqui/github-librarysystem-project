package com.example.lmsjavafx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentDashboard {

    public void start(Stage stage) {

        Label title = new Label("Welcome Student");
        title.setStyle("-fx-font-size:18px; -fx-font-weight:bold");

        Label info = new Label(
                "You can:\n" +
                        "- View Available Books\n" +
                        "- View Issued Books\n\n" +
                        "(More features coming soon)"
        );

        Button logoutBtn = new Button("Logout");

        logoutBtn.setOnAction(e -> {
            new LoginScreen().start(new Stage());
            stage.close();
        });

        VBox root = new VBox(15, title, info, logoutBtn);
        root.setPadding(new Insets(30));

        stage.setTitle("Student Panel");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}

