package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Maker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MakerRepository extends CrudRepository<Maker, UUID> {

}
