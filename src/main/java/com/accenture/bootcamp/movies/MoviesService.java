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

    @Autowired private JdbcTemplate jdbcTemplate;

    public List<Movie> movies(SortingOrder order) {
        String sqlQuery = "SELECT * FROM movies";
        List<Movie> movieList = jdbcTemplate.query(sqlQuery, this::mapRowToMovie);
        movieList.sort(order.getComparator());
        System.out.println("Getting list of movies.");
        return movieList;
    }

    public void create(Movie movie) {
        movie.setId(UUID.randomUUID().toString());
        String sqlQuery = "INSERT INTO movies(id, name, description, rating)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery, movie.getId(), movie.getName(), movie.getDescription(), movie.getRating());
        System.out.println("Creating movie " + movie.getId());
    }

    public void update(String id, Movie movie) {
        System.out.println("Updating movie " + id);
        String sqlQuery = "UPDATE movies SET name = ?, description = ?, rating = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, movie.getName(), movie.getDescription(), movie.getRating(), movie.getId());
    }

    public void delete(String id) {
        System.out.println("Deleting movie " + id);
        String sqlQuery = "DELETE FROM movies WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    private Movie mapRowToMovie(ResultSet resultSet,int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getString("id"));
        movie.setName(resultSet.getString("name"));
        movie.setDescription(resultSet.getString("description"));
        movie.setRating(resultSet.getFloat("rating"));
        return movie;
    }
}
