package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findByHighestPoint();

    List<Author> findAll();
}
