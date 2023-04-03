package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.ReadData;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface ReadDataRepository extends Neo4jRepository<ReadData,Long> {
@Query("MATCH (n:User)-[r:Read]->(b:Book) RETURN r LIMIT 3")
    List<ReadData> findAll();
}
