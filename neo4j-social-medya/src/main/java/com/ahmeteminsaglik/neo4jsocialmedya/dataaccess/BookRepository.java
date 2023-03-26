package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface BookRepository extends Neo4jRepository<Book, Long> {
    Book findByName(String name);


    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (b:Book) " +
            "RETURN b,u")

            /*MATCH (u:User) WHERE ID(u)=64
MATCH (b:Book)
Create (u)-[:Read]->(b)
RETURN u,b*/
//            "<-[ai:ACTED_IN]-(p:Person)-[d:DIRECTED]->(dm:Movie) return p, collect(ai), collect(d), collect(am), collect(dm)")
    List<Book> getAllByUserIdMatches(Long userId);
}
