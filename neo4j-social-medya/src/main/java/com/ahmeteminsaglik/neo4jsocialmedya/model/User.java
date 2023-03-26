package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
/*
* create (n:User{name:"Ahmet Emin",lastname:"SAGLIK",username:"ahmet",password:"pass",totalFollowers:0})*/
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastname;
    private String username;
    private String password;
    private int totalFollowers;

//    @JsonIgnoreProperties("person")
//    @Relationship(type = "ACTED_IN")
//    private List<Movie> actedIn = new ArrayList<>();
//
//    @JsonIgnoreProperties({"actors", "directors"})
//    @Relationship(type = "DIRECTED")
//    private List<Movie> directed = new ArrayList<>();

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
