package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends CrudRepository<Review, UUID> {

}
