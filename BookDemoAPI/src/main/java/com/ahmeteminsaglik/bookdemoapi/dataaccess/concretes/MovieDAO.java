package com.ahmeteminsaglik.bookdemoapi.dataaccess.concretes;

import com.ahmeteminsaglik.bookdemoapi.model.MovieEntity;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
//import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieDAO extends ReactiveNeo4jRepository<MovieEntity, Integer> {

    Mono<MovieEntity> getFirstByTitle(String title);

    Mono<MovieEntity> getById(int id);

    Flux<MovieEntity> getAll();

}
