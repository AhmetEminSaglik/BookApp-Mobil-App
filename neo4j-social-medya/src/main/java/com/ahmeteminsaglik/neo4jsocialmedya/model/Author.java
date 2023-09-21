package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NodeEntity
public class Author {
    private static Random random = new Random();
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastname;
    private int totalBook = random.nextInt(50) + 10;
    private double point = random.nextInt(5) + 1;
    private  String key;

    @Relationship(type = "Write", direction = Relationship.Direction.OUTGOING)
    private List<Book> bookList;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalBook=" + totalBook +
                ", point=" + point +
                ", key='" + key + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
