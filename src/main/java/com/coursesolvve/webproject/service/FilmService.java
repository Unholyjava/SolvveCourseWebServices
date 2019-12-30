package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.dto.FilmReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public FilmReadDTO getFilm(UUID id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Film.class, id);
        });
        return toRead(film);
    }

    private FilmReadDTO toRead(Film film) {
        FilmReadDTO filmReadDTO = new FilmReadDTO();
        filmReadDTO.setId(film.getId());
        filmReadDTO.setName(film.getName());
        filmReadDTO.setInfo(film.getInfo());
        filmReadDTO.setRatingFull(film.getRatingFull());
        filmReadDTO.setTextMistake(film.isTextMistake());
        filmReadDTO.setRelease(film.isRelease());
        filmReadDTO.setGenres(film.getGenres());
        return filmReadDTO;
    }
}
