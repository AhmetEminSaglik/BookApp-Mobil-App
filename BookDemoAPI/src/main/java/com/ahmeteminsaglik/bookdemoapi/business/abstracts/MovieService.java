package com.ahmeteminsaglik.bookdemoapi.business.abstracts;

import com.ahmeteminsaglik.bookdemoapi.model.MovieEntity;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
    Flux<MovieEntity> getAll();

    Mono<MovieEntity> getById(int id);
}
