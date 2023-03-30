package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.AuthorService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/recommend/point")
    public DataResult<List<Author>> getRecommenedAuthorListByHighestPoint(){
        return  new SuccessDataResult<>(authorService.findByHighestPoint(),"Data retrived Successfully");
    }
}
