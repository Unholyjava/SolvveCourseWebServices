package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepository extends CrudRepository<Like, UUID> {

}
