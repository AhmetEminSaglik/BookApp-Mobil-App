package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BookOLRepository extends Neo4jRepository<BookOL, Long> {
}
