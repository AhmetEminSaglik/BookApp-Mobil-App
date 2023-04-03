package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadData {
    @Id
    @GeneratedValue
    private Long id;

    private User user;
    private int rate;
    private Book book;

    @Override
    public String toString() {
        return "ReadData{" +
                "id=" + id +
                ", user=" + user +
                ", rate=" + rate +
                ", book=" + book +
                '}';
    }
}
