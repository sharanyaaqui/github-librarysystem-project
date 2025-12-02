package com.example.lmsjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Library Management Login");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField username = new TextField();
        PasswordField password = new PasswordField();

        ComboBox<String> role = new ComboBox<>();
        role.getItems().addAll("Student", "Librarian");
        role.setPromptText("Select Role");

        Button loginBtn = new Button("Login");
        Label status = new Label();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(12);
        grid.setHgap(12);

        grid.add(title, 0, 0, 2, 1);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(username, 1, 1);

        grid.add(new Label("Password:"), 0, 2);
        grid.add(password, 1, 2);

        grid.add(new Label("Role:"), 0, 3);
        grid.add(role, 1, 3);

        grid.add(loginBtn, 0, 4, 2, 1);
        grid.add(status, 0, 5, 2, 1);

        loginBtn.setOnAction(e -> {

            if (role.getValue() == null) {
                status.setText("Select Role!");
                return;
            }

            // ✅ HARD-CODED LOGIN (FOR PROJECT)
            if (role.getValue().equals("Student") &&
                    username.getText().equals("student") &&
                    password.getText().equals("123")) {

                new StudentDashboard().start(new Stage());
                stage.close();

            } else if (role.getValue().equals("Librarian") &&
                    username.getText().equals("admin") &&
                    password.getText().equals("admin123")) {

                new Main().start(new Stage()); // Your LMS GUI
                stage.close();

            } else {
                status.setText("Invalid Login!");
            }
        });

        stage.setTitle("Login");
        stage.setScene(new Scene(grid, 400, 350));
        stage.show();
    }
}
