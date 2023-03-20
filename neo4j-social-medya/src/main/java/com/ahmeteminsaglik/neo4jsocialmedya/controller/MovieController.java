package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.MovieService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping()
    public Iterable<Movie> getAll() {
        return movieService.findAll();
    }
    @GetMapping("/size")
    public int getSize() {
        return movieService.findAll().size();
    }
}
