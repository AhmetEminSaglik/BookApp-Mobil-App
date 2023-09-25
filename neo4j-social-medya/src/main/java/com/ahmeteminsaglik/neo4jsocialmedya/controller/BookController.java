package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getAll() {
        return service.findAll();
    }

    @GetMapping("/{name}")
    public Book getBookByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping()
    public DataResult<Book> save(@RequestBody Book book) {
        book = service.save(book);
        return new SuccessDataResult<>(book);
    }

    public ResponseEntity<DataResult<List<Book>>> saveAllBook(@RequestBody List<Book> list) {
        list = service.save(list);
        DataResult result = new SuccessDataResult(list, "BookOL list is saved.");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /*public ResponseEntity<DataResult<BookOL>> save(@RequestBody BookOL bookOL) {
        bookOL = bookOLService.save(bookOL);
        DataResult result = new SuccessDataResult(bookOL, "BookOL is saved.");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


*/
    @GetMapping("/readby/{userId}")

    public DataResult<List<Book>> getAllReadBookByUserId(@PathVariable long userId) {
        return new SuccessDataResult<>(service.getAllReadBooksByUserId(userId), "Read book data is retrived successfuly");
    }

    @GetMapping("/recommend/point")
    public DataResult<List<Book>> getRecommenedAuthorListByHighestPoint() {
        return new SuccessDataResult<>(service.findByHighestPoint(), "Data retrived Successfully");
    }

    @GetMapping("/recommend/totalread")
    public DataResult<List<Book>> getByHighestTotalRead() {
        return new SuccessDataResult<>(service.findByHighestTotalRead(), "Data retrived Successfully");
    }

    @GetMapping("/recommend/friend/{userId}")
    public DataResult<List<Book>> getByMostReadBookFromFollowings(@PathVariable long userId) {
        return new SuccessDataResult<>(service.findByMostReadBookFromFollowings(userId), "Data retrived Successfully");
    }

    @PostMapping("/{userId}/read/{bookId}")
    public Result createNewConnectionFollowUser(@PathVariable long userId, @PathVariable long bookId) {
        service.createConnectionUserReadBook(userId, bookId);
        return new SuccessResult("Connection is created");
    }

    @GetMapping("/fix")
    public void fixBookData() {
        service.fixBookData();
    }

}
