package com.ahmeteminsaglik.bookdemoapi.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    private int id;
    private String name;
    private String surname;
    private static int counter = 0;

    public Author(String name, String surname) {
        counter++;
        id = counter;
        this.name = name;
        this.surname = surname;
    }
}
