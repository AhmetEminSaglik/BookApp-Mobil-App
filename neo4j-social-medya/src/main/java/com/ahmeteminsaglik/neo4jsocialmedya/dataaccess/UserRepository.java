package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByName(String name);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Query("MATCH (u:User)-[r:Read]->(b:Book) return u,r,b limit 3")
    List<User> findAllWithBooks();

    @Query("MATCH (u:User)-[r:Read]->(b:Book)\n" +
            "where ID(u)=$userId\n" +
            "and ID(b)=$bookId\n" +
            "detach delete r")
    void removeUserReadBookConnection(long userId, long bookId);
}
