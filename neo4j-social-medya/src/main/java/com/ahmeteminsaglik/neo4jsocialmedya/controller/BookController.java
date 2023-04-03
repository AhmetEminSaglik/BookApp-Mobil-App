package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{name}")
    public Book getBookByName(@PathVariable String name) {
        return bookService.findByName(name);
    }

    @PostMapping()
    @ResponseBody
    public DataResult<Book> save(@RequestBody Book book) {
        book = bookService.save(book);
        return new SuccessDataResult<>(book);
    }

    @GetMapping("/readby/{userId}")
    public DataResult<List<Book>> getAllReadBookByUserId(@PathVariable Long userId) {
        return new SuccessDataResult<>(bookService.getAllReadBooksByUserId(userId), "Read book data is retrived successfuly");
    }

    @GetMapping("/recommend/point")
    public DataResult<List<Book>> getRecommenedAuthorListByHighestPoint() {
        return new SuccessDataResult<>(bookService.findByHighestPoint(), "Data retrived Successfully");
    }

    @GetMapping("/recommend/totalread")
    public DataResult<List<Book>> findByHighestTotalRead() {
        return new SuccessDataResult<>(bookService.findByHighestTotalRead(), "Data retrived Successfully");
    }

    @GetMapping("/recommend/friend/{userId}")
    public DataResult<List<Book>> findByMostReadBookFromFollowings(@PathVariable Long userId) {
        return new SuccessDataResult<>(bookService.findByMostReadBookFromFollowings(userId), "Data retrived Successfully");
    }

}
