package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookRepository extends Neo4jRepository<Book, Long> {
    Book findByName(String name);


    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (u)-[:READ]->(b:Book) " +
            "RETURN u,b")

            /*MATCH (u:User) WHERE ID(u)=64
MATCH (b:Book)
Create (u)-[:Read]->(b)
RETURN u,b*/
//            "<-[ai:ACTED_IN]-(p:Person)-[d:DIRECTED]->(dm:Movie) return p, collect(ai), collect(d), collect(am), collect(dm)")
    List<Book> getAllByUserIdMatches(@PathVariable Long userId);

    @Query("MATCH (b:Book)  RETURN b ORDER BY b.point DESC LIMIT 5")
    List<Book> findByHighestPoint();

    @Query("MATCH (b:Book) RETURN b ORDER BY b.totalRead DESC LIMIT 5")
    List<Book> findByHighestTotalRead();

    // returns user's following users' read common books to recommend users.
    @Query(" MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (u)-[:FOLLOW]->(u2:User)-[:READ]->(b:Book) " +
            "WHERE  NOT (b)<-[:READ]-(u) " +
            "WITH b, COUNT(DISTINCT u2) AS num_readers " +
            "ORDER BY num_readers DESC " +
            "LIMIT 5 " +
            "MATCH (u:User)-[:READ]->(b) " +
            "RETURN DISTINCT b")
    List<Book> findByMostReadBookFromFollowings(@PathVariable Long userId);

    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (b:Book) WHERE ID(b) = $bookId " +
            "MERGE (u)-[r:READ]->(b)")
    void createConnectionUserReadBook(long userId, long bookId);
}
