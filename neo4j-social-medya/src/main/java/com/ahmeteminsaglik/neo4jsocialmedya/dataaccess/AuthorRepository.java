package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface AuthorRepository extends Neo4jRepository<Author, Long> {
    @Query("MATCH(a:Author)" +
            "RETURN a ORDER BY a.point DESC LIMIT 2")
    List<Author> findByHighestPoint();

    @Query("MATCH (a:Author)-[:WRITE]->(b:Book)\nWITH a, COUNT(b) AS totalBook\nSET a.totalBook = totalBook\nWITH a\nMATCH (a:Author)-[:WRITE]->(b:Book)\nWITH a, sum(b.point) as totalPoints, count(b) as totalBooks\nSET a.point = round(totalPoints / totalBooks,2)")
    void fixAuthorData();

    @Query("MATCH (a:Author) WHERE ID(a) = $authorId " +
            "MATCH (b:Book) WHERE ID(b) = $bookId " +
            "MERGE (a)-[w:WRITE]->(b)")
    void setWriteConnection(long authorId, long bookId);

    Author findByKey(String key);
    @Query("MATCH (book:Book)<-[:WRITE]-(author:Author) " +
            "WHERE ID(book)= $bookId " +
            "return author")
    Author findAuthorOfBook(long bookId);
}
