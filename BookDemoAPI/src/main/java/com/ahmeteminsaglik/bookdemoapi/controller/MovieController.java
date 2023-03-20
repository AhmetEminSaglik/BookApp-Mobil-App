package com.ahmeteminsaglik.bookdemoapi.controller;

import com.ahmeteminsaglik.bookdemoapi.business.abstracts.MovieService;
import com.ahmeteminsaglik.bookdemoapi.model.MovieEntity;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping()
    public Flux<MovieEntity> getAll() {
        return movieService.getAll();
    }
}
