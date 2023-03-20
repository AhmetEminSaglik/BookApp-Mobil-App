package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;


import com.ahmeteminsaglik.neo4jsocialmedya.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    Movie findMovieByTitle(String title);

    Iterable<Movie> findMovieByTitleLike(String title);
}
