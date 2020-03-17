package com.accenture.bootcamp.movies;

import java.util.Comparator;

public enum SortingOrder {
    ASC, DESC, RATING;

    public Comparator<Movie> getComparator() {
        switch(this) {
            case ASC:
                break;
            case DESC:
                return Comparator.comparing(Movie::getName).reversed();
            case RATING:
                return Comparator.comparing(Movie::getRating).reversed();
        }
        return Comparator.comparing(Movie::getName);
    }
}
