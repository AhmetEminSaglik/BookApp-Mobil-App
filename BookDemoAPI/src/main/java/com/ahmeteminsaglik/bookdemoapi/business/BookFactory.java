package com.ahmeteminsaglik.bookdemoapi.business;

import com.ahmeteminsaglik.bookdemoapi.model.Author;

public class BookFactory {
    private String id;
    private String name;
    private Author author;

    public BookFactory(String id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
