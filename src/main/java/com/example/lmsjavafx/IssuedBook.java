package com.example.lmsjavafx;

import java.time.LocalDateTime;
import java.time.Duration;

public class IssuedBook {

    private Book book;
    private Student student;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;

    // ✅ Constructor used when issuing a book
    public IssuedBook(Student student, Book book) {
        this.book = book;
        this.student = student;
        this.issueDate = LocalDateTime.now();      // Issue date = now
        this.returnDate = issueDate.plusDays(7);  // Auto return after 7 days
    }

    // ✅ Required Getters (Used by Library & GUI)
    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    // ✅ Fine Calculation (₹10 per late day)
    public long getFine(LocalDateTime actualReturnDate) {
        long lateDays = Duration.between(returnDate, actualReturnDate).toDays();
        return lateDays > 0 ? lateDays * 10 : 0;
    }

    // ✅ For display in console or GUI
    @Override
    public String toString() {
        return "Book: " + book.getTitle() +
                " | Student: " + student.getName() +
                " | Issued: " + issueDate +
                " | Return By: " + returnDate;
    }
}
