package com.accenture.bootcamp.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sun.security.util.math.IntegerModuloP;

import javax.validation.Valid;

@Controller
public class MoviesControllerMVCThymeleaf {

    private final MovieRepository movieRepository;

    @Autowired
    public MoviesControllerMVCThymeleaf(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";
    }

    @GetMapping("/form")
    public String showFillUpForm(Movie movie) {
        return "add-movie";
    }

    @PostMapping("/addmovie")
    public String addMovie(Movie movie, Model model) {
        movieRepository.save(movie);
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid movie Id: " + id));
        model.addAttribute("movie", movie);
        return "update-movie";
    }

    @PostMapping("/update/{id}")
    public String updateMovie(@PathVariable("id") String id, Movie movie, Model model) {
        movieRepository.save(movie);
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") String id, Model model) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid movie Id: " + id));
        movieRepository.delete(movie);
        model.addAttribute("movies",movieRepository.findAll());
        return "movies";
    }
}
