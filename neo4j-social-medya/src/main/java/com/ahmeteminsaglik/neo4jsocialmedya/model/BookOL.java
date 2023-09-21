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
public class BookOL {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
//    private AuthorOL authorOL;
private  String authorKey;
    @Override
    public String toString() {
        return "BookOL{" +
                "\nid='" + id +
                ",\ntitle='" + title +
                ",\ndescription='" + description +
//                ",authors=" + authorOL +
                ",\nauthorKey=" + authorKey +
                '}';
    }
}
