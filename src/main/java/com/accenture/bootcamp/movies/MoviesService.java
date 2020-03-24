package com.accenture.bootcamp.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoviesService {

    private final MovieRepository movieRepository;

    @Autowired
    public MoviesService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Iterable<Movie> movies(SortingOrder order) {
        List<Movie> movieList = (List<Movie>) movieRepository.findAll();
        movieList.sort(order.getComparator());
        return movieList;
    }

    public void create(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie checkId(String id) {
        return movieRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid movie Id: " + id));
    }

    public void delete(String id) {
        movieRepository.delete(checkId(id));
    }
}
