package com.ahmeteminsaglik.bookdemoapi.controller;

import com.ahmeteminsaglik.bookdemoapi.business.ExampleData;
import com.ahmeteminsaglik.bookdemoapi.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    ExampleData exampleData = new ExampleData();

    @GetMapping("")
    public List<Book> getBookList() {
        return exampleData.getBookList();
    }
}
