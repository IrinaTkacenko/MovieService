package com.accenture.bootcamp.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MoviesControllerMVCThymeleaf {

    @Autowired
    MoviesService moviesService;

    @GetMapping("/movies")
    public String movies(@RequestParam(defaultValue = "ASC") SortingOrder sort, Model model) {
        model.addAttribute("movies", moviesService.movies(sort));
        return "movies";
    }

    @GetMapping("/form")
    public String showFillUpForm(Movie movie) {
        return "add-movie";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addmovie")
    public String addMovie(@RequestParam(defaultValue = "ASC") SortingOrder sort,
                           Movie movie, Model model) {
        moviesService.create(movie);
        return movies(sort, model);
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("movie", moviesService.checkId(id));
        return "update-movie";
    }

    @PostMapping("/update/{id}")
    public String updateMovie(@RequestParam(defaultValue = "ASC") SortingOrder sort,
                               Movie movie, Model model) {
        moviesService.create(movie);
        return movies(sort, model);
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@RequestParam(defaultValue = "ASC") SortingOrder sort,
                              @PathVariable("id") String id, Model model) {
        moviesService.delete(id);
        return movies(sort, model);
    }
}
