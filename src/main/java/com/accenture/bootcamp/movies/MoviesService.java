package com.accenture.bootcamp.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.asList;

@Service
public class MoviesService {
    private Map<String, Movie> movies = new HashMap<>();

    @Autowired private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initMovies() {

        Movie movie = new Movie();
        movie.setId(UUID.randomUUID().toString());
        movie.setName("1917");
        movie.setDescription("Its a movie about war and two friends saving comrades.");
        movie.setRating(8.3F);
        movie.setCast(asList("Benedict Cumberbatch", "George McKay"));
        movies.put(movie.getId(), movie);
    }

    public List<Movie> movies(SortingOrder order) {
        String sqlQuery = "SELECT * FROM movies";
        List<Movie> movieList = jdbcTemplate.query(sqlQuery, this::mapRowToMovie);
        movieList.sort(order.getComparator());
        System.out.println("Getting list of movies.");
        return movieList;
    }

    private Movie mapRowToMovie(ResultSet resultSet,int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getString("id"));
        movie.setName(resultSet.getString("movie_name"));
        movie.setDescription(resultSet.getString("movie_description"));
        movie.setRating(resultSet.getFloat("movie_rating"));
        return movie;
    }

    public void create(Movie movie) {
        movie.setId(UUID.randomUUID().toString());
        String sqlQuery = "INSERT INTO movies(id, movie_name, movie_description, movie_rating)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery, movie.getId(), movie.getName(), movie.getDescription(), movie.getRating());
        System.out.println("Creating movie " + movie.getId());
    }

    public void update(String id, Movie movie) {
        System.out.println("Updating movie " + id);
        String sqlQuery = "UPDATE movies SET movie_name = ?, " +
                                            "movie_description = ?, " +
                                            "movie_rating = ? " +
                                            "WHERE id = ?";
        jdbcTemplate.update(sqlQuery, movie.getName(), movie.getDescription(), movie.getRating(), movie.getId());
    }

    public void delete(String id) {
        System.out.println("Deleting movie " + id);
        String sqlQuery = "DELETE FROM movies WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
