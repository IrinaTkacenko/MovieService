//package com.accenture.bootcamp.movies;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class MoviesControllerUsingJPA {
//
//    @Autowired
//    MoviesService movies;
//
//    @GetMapping("/movies")
//    public Iterable<Movie> showMovies(@RequestParam(defaultValue = "ASC") SortingOrder sort) {
//        return movies.movies(sort);
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/movies")
//    public Iterable<Movie> create(@RequestBody Movie movie, @RequestParam(defaultValue = "ASC") SortingOrder sort) {
//        movies.create(movie);
//        return showMovies(sort);
//    }
//
//    @PutMapping("/movies/{id}")
//    public void update(@PathVariable String id, @RequestBody Movie movie) {
//        movies.update(id, movie);
//    }
//
//    @DeleteMapping("/movies/{id}")
//    public void delete(@PathVariable String id) {
//        movies.delete(id);
//    }
//}
