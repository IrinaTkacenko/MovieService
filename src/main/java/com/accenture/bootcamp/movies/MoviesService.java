package com.accenture.bootcamp.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoviesService {

    @Autowired private MovieRepository movieRepository;

    public Iterable<Movie> movies(SortingOrder order) {
        List<Movie> movieList = (List<Movie>) movieRepository.findAll();
        movieList.sort(order.getComparator());
        return movieList;
    }

    public void create(Movie movie) {
        movie.setId(UUID.randomUUID().toString());
        movieRepository.save(movie);
    }

    public void update(String id, Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()) {
            movieRepository.deleteById(id);
            movie.setId(id);
            movieRepository.save(movie);
        }
    }

    public void delete(String id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()) {
            movieRepository.deleteById(id);
        }
    }
}
