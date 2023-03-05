package com.pimentelprojects.movies.services;

import com.pimentelprojects.movies.models.Review;

public interface ReviewService {

    Review createReview(String reviewBody, String imdbId);
}
