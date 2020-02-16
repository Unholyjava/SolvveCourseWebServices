package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.dto.film.FilmCreateDTO;
import com.coursesolvve.webproject.dto.film.FilmPatchDTO;
import com.coursesolvve.webproject.dto.film.FilmPutDTO;
import com.coursesolvve.webproject.dto.film.FilmReadDTO;
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

    public FilmReadDTO createFilm(FilmCreateDTO create) {
        Film film = new Film();
        film.setName(create.getName());
        film.setInfo(create.getInfo());
        film.setRatingFull(create.getRatingFull());
        film.setTextMistake(create.getTextMistake());
        film.setRelease(create.getRelease());
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
        if (patch.getRatingFull() != null) {
            film.setRatingFull(patch.getRatingFull());
        }
        if (patch.getRelease() != null) {
            film.setRelease(patch.getRelease());
        }
        if (patch.getTextMistake() != null) {
            film.setTextMistake(patch.getTextMistake());
        }

        film = filmRepository.save(film);
        return toRead(film);
    }

    public FilmReadDTO updateFilm(UUID id, FilmPutDTO put) {
        Film film = getFilmRequired(id);

        film.setName(put.getName());
        film.setInfo(put.getInfo());
        film.setRatingFull(put.getRatingFull());
        film.setRelease(put.getRelease());
        film.setTextMistake(put.getTextMistake());

        film = filmRepository.save(film);
        return toRead(film);
    }

    public void deleteFilm(UUID id) {
        filmRepository.delete(getFilmRequired(id));
    }

    private Film getFilmRequired(UUID id) {
        return filmRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Film.class, id);
        });
    }

    private FilmReadDTO toRead(Film film) {
        FilmReadDTO filmReadDTO = new FilmReadDTO();
        filmReadDTO.setId(film.getId());
        filmReadDTO.setCreatedAt(film.getCreatedAt());
        filmReadDTO.setUpdatedAt(film.getUpdatedAt());
        filmReadDTO.setName(film.getName());
        filmReadDTO.setInfo(film.getInfo());
        filmReadDTO.setRatingFull(film.getRatingFull());
        filmReadDTO.setTextMistake(film.getTextMistake());
        filmReadDTO.setRelease(film.getRelease());
        return filmReadDTO;
    }
}
