package com.example.lmsjavafx;

public class Book {

    private int bookId;
    private String title;
    private String publisher;
    private String edition;
    private int copies;

    // ✅ Constructor
    public Book(int bookId, String title, String publisher, String edition, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.edition = edition;
        this.copies = copies;
    }

    // ✅ EXACT METHOD NAME REQUIRED BY JavaFX
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getEdition() {
        return edition;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }



}
