package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.List;
import java.util.Objects;


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
    private int totalFollowed;
//    @Relationship(type = "Read",direction = Relationship.Direction.OUTGOING)
//    private  List<Book> bookList;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return totalFollowers == user.totalFollowers && totalFollowed == user.totalFollowed && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, username, password, totalFollowers, totalFollowed);
    }
}
