package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NodeEntity
public class User {
    /*
     * create (n:User{name:"Ahmet Emin",lastname:"SAGLIK",username:"ahmet",password:"pass",totalFollowers:0})*/
    @Id
    private Long id;

    private String name;
    private String lastname;
    private String username;
    private String password;
    private int totalFollowers;
    private int totalFollowed;
    @Relationship(type = "Read",direction = Relationship.Direction.OUTGOING)
    private  List<Book> bookList;

    //    @JsonIgnoreProperties("person")
//    @Relationship(type = "ACTED_IN")
//    private List<Movie> actedIn = new ArrayList<>();
//
//    @JsonIgnoreProperties({"actors", "directors"})
//    @Relationship(type = "DIRECTED")
//    private List<Movie> directed = new ArrayList<>();
//    @Relationship(type = "Read", direction = Relationship.Direction.OUTGOING)
//    private List<Read> reads;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", totalFollowers=" + totalFollowers +
                '}';
    }
}
