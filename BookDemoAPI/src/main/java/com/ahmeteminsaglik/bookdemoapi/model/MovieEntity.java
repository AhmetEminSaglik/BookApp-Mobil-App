package com.ahmeteminsaglik.bookdemoapi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;

@Node("Movie")
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {


    @Id
    @GeneratedValue
    private Long id;
    /*@Id
        private final String title;*/
    private String title;
    @Property("tagline")
    private String description;

    /*@Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private List<Roles> actorsAndRoles;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private List<PersonEntity> directors = new ArrayList<>();*/

//    public MovieEntity(String title, String description) {
//        this.title = title;
//        this.description = description;
//    }

    // Getters omitted for brevity
}