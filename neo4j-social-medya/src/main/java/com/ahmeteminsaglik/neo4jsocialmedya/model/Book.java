package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double point;
    private String imgUrl;
    private int totalRead;
    private String description;
    private String isbn_13;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name +
                ", point=" + point +
                ", imgUrl=" + imgUrl +
                ", totalRead=" + totalRead +
                ", description='" + description +
                '}';
    }
}
