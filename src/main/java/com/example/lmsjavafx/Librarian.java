package com.example.lmsjavafx;

public class Librarian {
    private int librarianId;
    private String name;

    public Librarian(int librarianId, String name) {
        this.librarianId = librarianId;
        this.name = name;
    }

    public int getLibrarianId() { return librarianId; }
    public String getName() { return name; }
}
