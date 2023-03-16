package com.ahmeteminsaglik.bookdemoapi.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String name;
    private int pageNumber;
    private Author author;
    private static int counter = 0;

    public Book(String name, int pageNumber, Author author) {
        counter++;
        id = counter;
        this.name = name;
        this.pageNumber = pageNumber;
        this.author = author;
    }

}
