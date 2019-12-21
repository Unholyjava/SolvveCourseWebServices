package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ActorRepository extends CrudRepository<Actor, UUID> {

}
