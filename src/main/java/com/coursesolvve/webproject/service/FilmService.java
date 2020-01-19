package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.dto.FilmCreateDTO;
import com.coursesolvve.webproject.dto.FilmPatchDTO;
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
        Film film = getFilmRequired(id);
        return toRead(film);
    }

    private Film getFilmRequired(UUID id) {
        return filmRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Film.class, id);
        });
    }

    private FilmReadDTO toRead(Film film) {
        FilmReadDTO filmReadDTO = new FilmReadDTO();
        filmReadDTO.setId(film.getId());
        filmReadDTO.setName(film.getName());
        filmReadDTO.setInfo(film.getInfo());
        filmReadDTO.setRatingFull(film.getRatingFull());
        filmReadDTO.setTextMistake(film.isTextMistake());
        filmReadDTO.setRelease(film.isRelease());
        return filmReadDTO;
    }

    public FilmReadDTO createFilm(FilmCreateDTO create) {
        Film film = new Film();
        film.setName(create.getName());
        film.setInfo(create.getInfo());
        film.setRatingFull(create.getRatingFull());
        film.setTextMistake(create.isTextMistake());
        film.setRelease(create.isRelease());
        film = filmRepository.save(film);
        return toRead(film);
    }

    public FilmReadDTO patchFilm(UUID id, FilmPatchDTO patch) {
        Film film = getFilmRequired(id);

        if (patch.getName() != null) {
            film.setName(patch.getName());
        }
        if (patch.getInfo() != null) {
            film.setInfo(patch.getInfo());
        }

        film.setRatingFull(patch.getRatingFull());
        film.setRelease(patch.isRelease());
        film.setTextMistake(patch.isTextMistake());

        film = filmRepository.save(film);
        return toRead(film);
    }

    public void deleteFilm(UUID id) {
        filmRepository.delete(getFilmRequired(id));
    }
}
