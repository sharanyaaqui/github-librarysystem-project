package com.example.lmsjavafx;

import java.time.LocalDateTime;
import java.util.*;

public class Library {

    // ✅ PRIVATE DATA (correct)
    private List<Book> books = new ArrayList<>();
    private List<IssuedBook> issuedBooks = new ArrayList<>();

    // ✅ SINGLE CORRECT GETTER (duplicate removed)
    public List<Book> getAllBooks() {
        return books;
    }

    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBooks;
    }

    // ✅ ADD BOOK
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // ✅ REMOVE BOOK
    public void removeBook(int bookId) {
        books.removeIf(b -> b.getBookId() == bookId);
        System.out.println("Book removed successfully!");
    }

    // ✅ CONSOLE DISPLAY (OPTIONAL)
    public void showBooks() {
        System.out.println("\n--- Available Books ---");
        for (Book b : books) {
            System.out.println(
                    b.getBookId() + " | " +
                            b.getTitle() + " | Copies: " + b.getCopies()
            );
        }
    }

    public void showIssuedBooks() {
        System.out.println("\n--- Issued Books ---");
        for (IssuedBook ib : issuedBooks) {
            System.out.println(ib);
        }
    }

    // ✅ ✅ ISSUE BOOK — FIXED PARAMETER ORDER + DECREASE
    public void issueBook(int bookId, Student student) {
        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (book.getCopies() <= 0) {
                    System.out.println("Book out of stock!");
                    return;
                }

                // ✅ DECREASE COPIES
                book.setCopies(book.getCopies() - 1);

                // ✅ FIXED CONSTRUCTOR ORDER
                issuedBooks.add(new IssuedBook(student,book));

                System.out.println("Book issued successfully!");
                return;
            }
        }

        System.out.println("Book not found!");
    }

    // ✅ ✅ RETURN BOOK — FIXED INCREASE + SAFE REMOVE + FINE
    public long returnBook(int bookId, Student student) {

        Iterator<IssuedBook> iterator = issuedBooks.iterator();

        while (iterator.hasNext()) {
            IssuedBook ib = iterator.next();

            if (ib.getBook().getBookId() == bookId &&
                    ib.getStudent().getStudentId() == student.getStudentId()) {

                // ✅ INCREASE COPIES
                Book book = ib.getBook();
                book.setCopies(book.getCopies() + 1);

                long fine = ib.getFine(LocalDateTime.now());

                // ✅ SAFE REMOVE (NO CRASH)
                iterator.remove();

                return fine;
            }
        }

        System.out.println("Issued book record not found!");
        return 0;
    }
}

