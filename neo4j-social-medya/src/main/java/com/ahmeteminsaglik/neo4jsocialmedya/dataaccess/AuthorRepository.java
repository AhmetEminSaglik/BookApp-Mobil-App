package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface AuthorRepository extends Neo4jRepository<Author, Long> {
    @Query("MATCH(a:Author)" +
           "RETURN a ORDER BY a.point DESC LIMIT 3")
    List<Author> findByHighestPoint();
}
