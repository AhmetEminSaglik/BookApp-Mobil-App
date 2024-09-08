package com.ahmeteminsaglik.neo4jsocialmedia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Objects;
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
    @Index(unique = true)
    private String key;

    @Relationship(type = "Write", direction = Relationship.Direction.OUTGOING)
    private List<Book> bookList;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", key='" + key + '\'' +
                ", bookList=" + bookList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(key, author.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
