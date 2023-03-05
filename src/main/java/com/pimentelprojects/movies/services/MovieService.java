package com.pimentelprojects.movies.services;

import com.pimentelprojects.movies.models.Movie;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAllMovies();

    Optional<Movie> findMovieByImdbId(String id);
}
