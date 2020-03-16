//package com.accenture.bootcamp.movies;
//
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.UUID;
//
//import static java.util.Arrays.asList;
//
//@Service
//public class MovieServiceWithList {
//
//    private List<Movie> movies = new ArrayList<>();
//
//    @PostConstruct
//    public void initMovies() {
//        Movie movie = new Movie();
//        movie.setId(UUID.randomUUID().toString());
//        movie.setName("1917");
//        movie.setDescription("Its a movie about war and two friends saving comrades.");
//        movie.setRating(8.3F);
//        movie.setCast(asList("Benedict Cumberbatch", "George McKay"));
//        movies.add(movie);
//    }
//
//    public List<Movie> movies() {
//        return movies;
//    }
//
//    public void create(Movie movie) {
//        movie.setId(UUID.randomUUID().toString());
//        System.out.println("Creating movie " + movie.getId());
//        movies.add(movie);
//    }
//
//    public void update(String id, Movie movie) {
//        System.out.println("Updating movie " + movie.getId());
//        for (Movie element:movies) {
//            if(element.getId().equals(id)) {
//                element.setName(movie.getName());
//                element.setDescription(movie.getDescription());
//                element.setRating(movie.getRating());
//                element.setCast(movie.getCast());
//                break;
//            }
//        }
//    }
//
//    public void delete(String id) {
//        System.out.println("Deleting movie " + id);
//        movies.removeIf(movie -> movie.getId().equals(id));
//
//        //The way to remove using Iterator
////        Iterator<Movie> movieIterator = movies.iterator();
////        while (movieIterator.hasNext()) {
////            if(movieIterator.next().getId().equals(id)) {
////                movieIterator.remove();
////            }
////        }
//
//        //Don't ever do it this way(even though it seems to work)
////        int toRemove = -1;
////        for (int i = 0; i < movies.size(); i++) {
////            if(movies.get(i).getId().equals(id)) {
////                toRemove = i;
////                break;
////            }
////        }
////        if(toRemove != -1) {
////            movies.remove(toRemove);
////        }
//    }
//}
