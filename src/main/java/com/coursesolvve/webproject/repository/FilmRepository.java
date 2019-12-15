package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Film;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface FilmRepository extends CrudRepository<Film, UUID> {

}
