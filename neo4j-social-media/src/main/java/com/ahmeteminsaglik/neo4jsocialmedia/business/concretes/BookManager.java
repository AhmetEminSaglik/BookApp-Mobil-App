package com.ahmeteminsaglik.neo4jsocialmedia.business.concretes;

import com.ahmeteminsaglik.neo4jsocialmedia.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedia.dataaccess.AuthorRepository;
import com.ahmeteminsaglik.neo4jsocialmedia.dataaccess.BookRepository;
import com.ahmeteminsaglik.neo4jsocialmedia.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookManager.class);
    @Autowired
    private BookRepository repo;
    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public List<Book> findAll() {
        List<Book> bookList = repo.findAll();
        bookList = setAuthorOfBooksByMatching(bookList);
        return bookList;
    }

    @Override
    public Book findByName(String name) {
        Book book = repo.findByName(name);
        book = setAuthorOfBookByMatching(book);
        return book;
    }

    @Override
    public Book save(Book book) {
        return repo.save(book);
    }

    @Override
    public Book getBookByUserIdReadBookId(long userId, long bookId) {
        return repo.getBookByUserIdReadBookId(userId, bookId);
    }

    @Override
    public List<Book> save(List<Book> list) {
        return repo.saveAll(list);
    }

    @Override
    public void removeUserReadBookConnection(long userId, long bookId) {
        repo.removeUserReadBookConnection(userId, bookId);
    }

    @Override
    public List<Book> getAllReadBooksByUserId(long userId) {
        List<Book> bookList = repo.getAllByUserIdMatches(userId);
        bookList = setAuthorOfBooksByMatching(bookList);
        return bookList;
    }

    @Override
    public List<Book> findByHighestPoint() {
        List<Book> bookList = repo.findByHighestPoint();
        bookList = setAuthorOfBooksByMatching(bookList);
        return bookList;
    }

    @Override
    public List<Book> findByHighestTotalRead() {
        List<Book> bookList = repo.findByHighestTotalRead();
        bookList = setAuthorOfBooksByMatching(bookList);
        return bookList;
    }

    @Override
    public void createConnectionUserReadBook(long userId, long bookId, int rate) {
        repo.createConnectionUserReadBook(userId, bookId, rate);
    }

    @Override
    public void fixBookData() {
        repo.fixBookData();
    }

    @Override
    public int getUserReadBookCount(long userId) {
        return repo.getUserReadBookCount(userId);
    }

    @Override
    public List<Book> findByMostReadBookFromFollowings(long userId) {
        List<Book> bookList = repo.findByMostReadBookFromFollowings(userId);
        bookList = setAuthorOfBooksByMatching(bookList);
        return bookList;
    }

    private List<Book> setAuthorOfBooksByMatching(List<Book> bookList) {
        bookList.forEach(e -> {
            e.setAuthor(authorRepo.findAuthorOfBook(e.getId()));
        });
        return bookList;
    }

    private Book setAuthorOfBookByMatching(Book book) {
        book.setAuthor(authorRepo.findAuthorOfBook(book.getId()));
        return book;
    }
}
