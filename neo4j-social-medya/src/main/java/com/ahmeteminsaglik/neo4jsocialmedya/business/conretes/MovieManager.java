package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.MovieService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.MovieRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieManager implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Iterable<Movie> findMovieByTitleLike(String title) {
        return movieRepository.findMovieByTitleLike(title);
    }
}
