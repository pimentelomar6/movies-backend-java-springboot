package com.pimentelprojects.movies.services;

import com.pimentelprojects.movies.models.Movie;
import com.pimentelprojects.movies.models.Review;
import com.pimentelprojects.movies.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;

    private MongoTemplate mongoTemplate;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Review createReview(String reviewBody, String imdbId) {

        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
