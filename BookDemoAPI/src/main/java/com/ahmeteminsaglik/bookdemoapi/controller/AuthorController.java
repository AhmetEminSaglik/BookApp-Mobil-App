package com.ahmeteminsaglik.bookdemoapi.controller;

import com.ahmeteminsaglik.bookdemoapi.business.ExampleData;
import com.ahmeteminsaglik.bookdemoapi.model.Author;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    ExampleData exampleData = new ExampleData();
    @GetMapping("")
    public List<Author> getAuthorList(){
        return  exampleData.getAuthorList();
    }
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable int authorId){
        return exampleData.getAuthorList().get(authorId-1);
    }
}
