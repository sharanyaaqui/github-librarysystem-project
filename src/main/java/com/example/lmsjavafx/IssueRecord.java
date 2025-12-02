package com.example.lmsjavafx;

import java.time.LocalDate;

public class IssueRecord {
    private Student student;
    private Book book;
    private LocalDate issueDate;
    private Librarian librarian;

    public IssueRecord(Student student, Book book, LocalDate issueDate, Librarian librarian) {
        this.student = student;
        this.book = book;
        this.issueDate = issueDate;
        this.librarian = librarian;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    @Override
    public String toString() {
        return student.getName() + " issued " + book.getTitle() +
                " on " + issueDate + " by " + librarian.getName();
    }
}

