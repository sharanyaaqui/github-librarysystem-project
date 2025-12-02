package com.example.lmsjavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    Library library = new Library();
    Librarian librarian = new Librarian(101, "Mr. Sharma");
    Student student = new Student(201, "Asha");

    @Override
    public void start(Stage stage) {

        // Preload Books
        library.addBook(new Book(1, "Java", "Pearson", "3rd", 3));
        library.addBook(new Book(2, "DSA", "McGraw", "2nd", 2));

        // ================= LOGIN =================
        Label title = new Label("Library Login");
        TextField idField = new TextField();
        idField.setPromptText("Enter ID");

        PasswordField pass = new PasswordField();
        pass.setPromptText("Password = ID");

        Button libBtn = new Button("Login as Librarian");
        Button stuBtn = new Button("Login as Student");

        VBox login = new VBox(15, title, idField, pass, libBtn, stuBtn);
        login.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(login, 400, 400);

        // ================= LIBRARIAN DASHBOARD =================
        TextArea libArea = new TextArea();
        TextField bookId = new TextField();
        TextField titleField = new TextField();
        TextField pub = new TextField();
        TextField ed = new TextField();
        TextField copies = new TextField();

        Button addBook = new Button("Add Book");
        Button removeBook = new Button("Remove Book");
        Button showBooks = new Button("Show Books");
        Button showIssued = new Button("Show Issued Books");
        Button logoutLib = new Button("Logout");

        addBook.setOnAction(e -> {
            library.addBook(new Book(
                    Integer.parseInt(bookId.getText()),
                    titleField.getText(),
                    pub.getText(),
                    ed.getText(),
                    Integer.parseInt(copies.getText())));
        });

        showBooks.setOnAction(e -> {
            libArea.setText("");
            for (Book b : library.getAllBooks())
                libArea.appendText(b + "\n");
        });

        showIssued.setOnAction(e -> {
            libArea.setText("");
            for (IssuedBook ib : library.getAllIssuedBooks())
                libArea.appendText(ib + "\n");
        });

        logoutLib.setOnAction(e -> stage.setScene(loginScene));

        VBox librarianUI = new VBox(10,
                new Label("Book ID"), bookId,
                new Label("Title"), titleField,
                new Label("Publisher"), pub,
                new Label("Edition"), ed,
                new Label("Copies"), copies,
                addBook, removeBook,
                showBooks, showIssued, logoutLib, libArea);

        Scene libScene = new Scene(librarianUI, 500, 600);

        // ================= STUDENT DASHBOARD =================
        TextField bid = new TextField();
        TextArea stuArea = new TextArea();
        Button issue = new Button("Issue Book");
        Button ret = new Button("Return Book");
        Button logoutStu = new Button("Logout");

        issue.setOnAction(e ->
                library.issueBook(Integer.parseInt(bid.getText()), student));

        ret.setOnAction(e -> {
            long fine = library.returnBook(Integer.parseInt(bid.getText()), student);
            stuArea.setText("Fine ₹" + fine);
        });

        logoutStu.setOnAction(e -> stage.setScene(loginScene));

        VBox studentUI = new VBox(10,
                new Label("Book ID"), bid,
                issue, ret, logoutStu, stuArea);

        Scene stuScene = new Scene(studentUI, 400, 400);

        // ================= LOGIN LOGIC =================
        libBtn.setOnAction(e -> {
            if (Integer.parseInt(idField.getText()) == librarian.getLibrarianId())
                stage.setScene(libScene);
        });

        stuBtn.setOnAction(e -> {
            if (Integer.parseInt(idField.getText()) == student.getStudentId())
                stage.setScene(stuScene);
        });

        stage.setTitle("Library Management System");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
