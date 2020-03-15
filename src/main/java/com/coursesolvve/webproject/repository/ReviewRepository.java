package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends CrudRepository<Review, UUID> {

    @Query("select avg(r.filmRating) from Review r where r.film.id = :filmId")
    Double calcAverageRatingOfFilm(UUID filmId);
}
