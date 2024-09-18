package org.ahmeteminsaglik.bookapp.business.abstracts;

import org.ahmeteminsaglik.bookapp.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findByHighestPoint();

    void fixAuthorData();

    List<Author> findAll();

    List<Author> saveAll(List<Author> authorList);

    void setWriteConnection(long authorId, long bookId);

    Author findByKey(String authorKey);
}
