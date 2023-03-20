package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;


import com.ahmeteminsaglik.neo4jsocialmedya.model.Movie;

import java.util.List;

public interface MovieService {
    Movie findMovieByTitle(String title);
    List<Movie> findAll();

    Iterable<Movie> findMovieByTitleLike(String title);

}
