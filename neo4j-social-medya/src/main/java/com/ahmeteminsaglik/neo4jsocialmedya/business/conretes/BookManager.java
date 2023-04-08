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
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllReadBooksByUserId(Long userId) {
        return bookRepository.getAllByUserIdMatches(userId);
    }

    @Override
    public List<Book> findByHighestPoint() {
        return bookRepository.findByHighestPoint();
    }

    @Override
    public List<Book> findByHighestTotalRead() {
        return bookRepository.findByHighestTotalRead();
    }

    @Override
    public List<Book> findByMostReadBookFromFollowings(Long userId) {
        return bookRepository.findByMostReadBookFromFollowings(userId);
    }
}
