package com.ahmeteminsaglik.bookdemoapi.business.conretes;

import com.ahmeteminsaglik.bookdemoapi.business.abstracts.MovieService;
import com.ahmeteminsaglik.bookdemoapi.dataaccess.concretes.MovieDAO;
import com.ahmeteminsaglik.bookdemoapi.model.MovieEntity;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieManager implements MovieService {
    private MovieDAO movieDAO;

    @Override
    public Flux<MovieEntity> getAll() {
        return movieDAO.getAll();
    }

    @Override
    public Mono<MovieEntity> getById(int id) {
        return movieDAO.getById(id);
    }
}
