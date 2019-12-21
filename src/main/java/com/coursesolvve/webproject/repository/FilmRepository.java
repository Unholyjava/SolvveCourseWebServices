package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FilmRepository extends CrudRepository<Film, UUID> {

}
