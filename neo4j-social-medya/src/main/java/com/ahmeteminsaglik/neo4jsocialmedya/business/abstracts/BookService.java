package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findByName(String name);

    Book save(Book book);

    List<Book> getAllReadBooksByUserId(Long userId);

    List<Book> findByHighestPoint();
    List<Book> findByHighestTotalRead();
    List<Book> findByMostReadBookFromFollowings(Long userId);

}
