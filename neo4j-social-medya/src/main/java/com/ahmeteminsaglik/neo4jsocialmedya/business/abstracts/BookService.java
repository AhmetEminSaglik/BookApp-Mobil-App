package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findByName(String name);

    Book save(Book book);

    Book getBookByUserIdReadBookId(long userId, long bookId);

    List<Book> save(List<Book> list);
    void removeUserReadBookConnection(long userId, long bookId);
    List<Book> getAllReadBooksByUserId(long userId);

    List<Book> findByHighestPoint();

    List<Book> findByHighestTotalRead();

    List<Book> findByMostReadBookFromFollowings(long userId);

    void createConnectionUserReadBook(long userId, long bookId,int rate);

    void fixBookData();
    int getUserReadBookCount(long userId);

}
