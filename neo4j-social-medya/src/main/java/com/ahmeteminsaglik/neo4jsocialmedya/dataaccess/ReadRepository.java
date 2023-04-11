package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Read;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface ReadRepository extends Neo4jRepository<Read, Long> {
    @Query("MATCH (u:User)-[r:READ]->(b:Book) RETURN u,r,b LIMIT 3")
    List<Read> findAll();
}
