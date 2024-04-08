package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookRepository extends Neo4jRepository<Book, Long> {
    Book findByName(String name);

    @Query("MATCH (u:User)-[r:READ]->(b:Book) WHERE ID(u) = $userId " +
            "RETURN b " +
            "ORDER BY r.timestamp DESC")
    List<Book> getAllByUserIdMatches(@PathVariable Long userId);

    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (u)-[:READ]->(b:Book) WHERE ID(b) = $bookId " +
            "RETURN b")
    Book getBookByUserIdReadBookId(@PathVariable Long userId, @PathVariable Long bookId);

    @Query("MATCH (b:Book)  RETURN b ORDER BY b.point DESC LIMIT 2 ")
    List<Book> findByHighestPoint();

    @Query("MATCH (b:Book) RETURN b ORDER BY b.totalRead DESC LIMIT 2 ")
    List<Book> findByHighestTotalRead();

    @Query(" MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (u)-[:FOLLOW]->(u2:User)-[:READ]->(b:Book) " +
            "WHERE  NOT (b)<-[:READ]-(u) " +
            "WITH b, COUNT(DISTINCT u2) AS num_readers " +
            "ORDER BY num_readers DESC " +
            "LIMIT 2  " +
            "MATCH (u:User)-[:READ]->(b) " +
            "RETURN DISTINCT b")
    List<Book> findByMostReadBookFromFollowings(@PathVariable Long userId);


    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (b:Book) WHERE ID(b) = $bookId " +
            "MERGE (u)-[r:READ{rate:$rate}]->(b) " +
            "SET r.timestamp = datetime() "
    )
    void createConnectionUserReadBook(long userId, long bookId, int rate);

    @Query("MATCH (u:User)-[r:READ]->(b:Book) " +
            "WHERE ID(u)=$userId " +
            "AND ID(b)=$bookId " +
            "DETACH DELETE r")
    void removeUserReadBookConnection(long userId, long bookId);

    @Query("MATCH (b:Book)<-[r:READ]-(u:User)\nWITH b, avg(r.rate) as point, count(u) as totalReaders\nSET b.totalRead = totalReaders, b.point = round(point, 1)\n")
    void fixBookData();

    @Query("match (u:User)-[:READ]->(b:Book) \n " +
            "WHERE ID(u) = $userId " +
            " RETURN COUNT(b)")
    int getUserReadBookCount(long userId);
}
