package com.example.lmsjavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsoleMain extends Application {

    private Library library = new Library();
    private TableView<Book> table = new TableView<>();

    // ✅ Sample users
    private Student s1 = new Student(201, "Rahul");
    private Student s2 = new Student(202, "Priya");
    private Librarian lib1 = new Librarian(101, "Mr. Sharma");

    @Override
    public void start(Stage stage) {

        // ✅ Preload books
        library.addBook(new Book(1001, "Java Programming", "Pearson", "3rd", 2));
        library.addBook(new Book(1002, "Data Structures", "McGraw Hill", "2nd", 3));

        Label title = new Label("Library Management System");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // ================= TABLE =================
        TableColumn<Book, Integer> idCol = new TableColumn<>("Book ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> pubCol = new TableColumn<>("Publisher");
        pubCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        TableColumn<Book, String> edCol = new TableColumn<>("Edition");
        edCol.setCellValueFactory(new PropertyValueFactory<>("edition"));

        TableColumn<Book, Integer> copyCol = new TableColumn<>("Copies");
        copyCol.setCellValueFactory(new PropertyValueFactory<>("copies"));

        table.getColumns().addAll(idCol, titleCol, pubCol, edCol, copyCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button loadBooksBtn = new Button("Refresh Book List");
        loadBooksBtn.setOnAction(e -> refreshTable());

        // ================= ISSUE / RETURN FORM =================
        TextField studentIdField = new TextField();
        TextField bookIdField = new TextField();

        studentIdField.setPromptText("Student ID");
        bookIdField.setPromptText("Book ID");

        Button issueBtn = new Button("Issue Book");
        Button returnBtn = new Button("Return Book");

        Label statusLabel = new Label();

        // ✅ ISSUE BOOK ACTION (FIXED)
        issueBtn.setOnAction(e -> {
            try {
                int sid = Integer.parseInt(studentIdField.getText());
                int bid = Integer.parseInt(bookIdField.getText());

                Student student = (sid == 201) ? s1 : (sid == 202) ? s2 : null;

                if (student == null) {
                    statusLabel.setText("Invalid Student ID");
                    return;
                }

                // ✅ CORRECT METHOD CALL
                library.issueBook(bid, student);

                refreshTable();
                statusLabel.setText("Book Issued Successfully!");

            } catch (Exception ex) {
                statusLabel.setText("Invalid Input!");
            }
        });

        // ✅ RETURN BOOK ACTION (FIXED)
        returnBtn.setOnAction(e -> {
            try {
                int sid = Integer.parseInt(studentIdField.getText());
                int bid = Integer.parseInt(bookIdField.getText());

                Student student = (sid == 201) ? s1 : (sid == 202) ? s2 : null;

                if (student == null) {
                    statusLabel.setText("Invalid Student ID");
                    return;
                }

                // ✅ CORRECT METHOD CALL
                long fine = library.returnBook(bid, student);

                refreshTable();

                if (fine > 0)
                    statusLabel.setText("Book Returned. Fine: ₹" + fine);
                else
                    statusLabel.setText("Book Returned Successfully!");

            } catch (Exception ex) {
                statusLabel.setText("Invalid Input!");
            }
        });

        // ================= FORM LAYOUT =================
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Student ID:"), 0, 0);
        form.add(studentIdField, 1, 0);

        form.add(new Label("Book ID:"), 0, 1);
        form.add(bookIdField, 1, 1);

        form.add(issueBtn, 0, 2);
        form.add(returnBtn, 1, 2);

        form.add(statusLabel, 0, 3, 2, 1);

        VBox root = new VBox(15, title, loadBooksBtn, table, form);
        root.setPadding(new Insets(20));

        stage.setTitle("LMS - JavaFX");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        refreshTable();
    }

    // ✅ Refresh table directly from backend (FIXED)
    private void refreshTable() {
        ObservableList<Book> bookList =
                FXCollections.observableArrayList(library.getAllBooks());
        table.setItems(bookList);
    }
}

