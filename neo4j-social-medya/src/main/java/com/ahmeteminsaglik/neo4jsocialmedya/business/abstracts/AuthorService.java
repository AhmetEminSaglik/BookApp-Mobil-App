package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findByHighestPoint();

    void fixAuthorData();

    List<Author> findAll();

    List<Author> saveAll(List<Author> authorList);
    void setWriteConnection(long authorId, long bookId);
    Author findByKey(String authorKey);
}
