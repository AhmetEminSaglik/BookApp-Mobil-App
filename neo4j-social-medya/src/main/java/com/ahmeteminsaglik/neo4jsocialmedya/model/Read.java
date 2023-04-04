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
import org.springframework.data.neo4j.core.schema.RelationshipId;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RelationshipEntity(type = "Read")
public class Read {
    @RelationshipId
    private Long id;

    @StartNode
//    @JsonIgnoreProperties("Read")
    private User user;

    private int rate;

    @EndNode
//    @JsonIgnoreProperties("Read")
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
  /*  @Id
//    @GeneratedValue
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
    }*/
}
