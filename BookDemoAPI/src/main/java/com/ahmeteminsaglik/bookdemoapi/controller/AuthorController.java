package com.ahmeteminsaglik.bookdemoapi.controller;

import com.ahmeteminsaglik.bookdemoapi.business.ExampleData;
import com.ahmeteminsaglik.bookdemoapi.model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    ExampleData exampleData = new ExampleData();
    @GetMapping("")
    public List<Author> getAuthorList(){
        return  exampleData.getAuthorList();
    }
}
