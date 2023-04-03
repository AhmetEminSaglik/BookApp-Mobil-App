package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RelationshipEntity(type = "Read")
public class Read {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User user;

    private int rate;

    @EndNode
    private Book book;

    @Override
    public String toString() {
        return "Read{" +
                "id=" + id +
                ", rate=" + rate +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
