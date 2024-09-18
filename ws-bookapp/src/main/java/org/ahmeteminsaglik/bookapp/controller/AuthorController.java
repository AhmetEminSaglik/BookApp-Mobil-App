package org.ahmeteminsaglik.bookapp.controller;

import org.ahmeteminsaglik.bookapp.business.abstracts.AuthorService;
import org.ahmeteminsaglik.bookapp.model.Author;
import org.ahmeteminsaglik.bookapp.model.Book;
import org.ahmeteminsaglik.bookapp.utility.result.DataResult;
import org.ahmeteminsaglik.bookapp.utility.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping
    public DataResult<List<Author>> getAllAuthor() {
        return new SuccessDataResult<>(service.findAll());
    }

    @GetMapping("/key/{key}")
    public Author findByKey(@PathVariable String key) {
        return service.findByKey(key);
    }

    @GetMapping("/recommend/point")
    public DataResult<List<Author>> getRecommenedAuthorListByHighestPoint() {
        return new SuccessDataResult<>(service.findByHighestPoint(), "Data retrived Successfully");
    }

    @PostMapping()
    public DataResult<List<Author>> saveAll(List<Author> authorList) {
        return new SuccessDataResult<>(service.saveAll(authorList), "Author List saved");
    }

    @PostMapping("/write")
    public DataResult<String> setWriteConnection(Author author, List<Book> bookList) {
        for (Book book : bookList) {
            service.setWriteConnection(author.getId(), book.getId());
        }
        return new SuccessDataResult<>("Set connections between author and books ");

    }

    @GetMapping("/fix")
    public void fixAuthorData() {
        service.fixAuthorData();
    }
}
