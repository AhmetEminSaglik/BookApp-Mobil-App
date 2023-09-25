package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.BookRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements BookService {
    @Autowired
    private BookRepository repo;

    @Override
    public List<Book> findAll() {
        return repo.findAll();
    }

    @Override
    public Book findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Book save(Book book) {
        return repo.save(book);
    }

    @Override
    public List<Book> save(List<Book> list) {
        return repo.saveAll(list);
    }

    @Override
    public List<Book> getAllReadBooksByUserId(long userId) {
        return repo.getAllByUserIdMatches(userId);
    }

    @Override
    public List<Book> findByHighestPoint() {
        return repo.findByHighestPoint();
    }

    @Override
    public List<Book> findByHighestTotalRead() {
        return repo.findByHighestTotalRead();
    }

    @Override
    public void createConnectionUserReadBook(long userId, long bookId) {
        repo.createConnectionUserReadBook(userId, bookId);
    }

    @Override
    public void fixBookData() {
        repo.fixBookData();
    }

    @Override
    public List<Book> findByMostReadBookFromFollowings(long userId) {
        return repo.findByMostReadBookFromFollowings(userId);
    }
}
