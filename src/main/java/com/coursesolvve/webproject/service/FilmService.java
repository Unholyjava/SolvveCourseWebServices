package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.dto.film.FilmCreateDTO;
import com.coursesolvve.webproject.dto.film.FilmPatchDTO;
import com.coursesolvve.webproject.dto.film.FilmPutDTO;
import com.coursesolvve.webproject.dto.film.FilmReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.FilmRepository;
import com.coursesolvve.webproject.repository.RepositoryHelper;
import com.coursesolvve.webproject.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RepositoryHelper repositoryHelper;

    @Autowired
    private ReviewRepository reviewRepository;

    public FilmReadDTO getFilm(UUID id) {
        Film film = repositoryHelper.getEntityRequired(Film.class, id);
        return toRead(film);
    }

    public FilmReadDTO createFilm(FilmCreateDTO create) {
        Film film = new Film();
        film.setName(create.getName());
        film.setInfo(create.getInfo());
        film.setAverageRating(create.getAverageRating());
        film.setTextMistake(create.getTextMistake());
        film.setRelease(create.getRelease());
        film = filmRepository.save(film);
        return toRead(film);
    }

    public FilmReadDTO patchFilm(UUID id, FilmPatchDTO patch) {
        Film film = repositoryHelper.getEntityRequired(Film.class, id);

        if (patch.getName() != null) {
            film.setName(patch.getName());
        }
        if (patch.getInfo() != null) {
            film.setInfo(patch.getInfo());
        }
        if (patch.getAverageRating() != null) {
            film.setAverageRating(patch.getAverageRating());
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
        Film film = repositoryHelper.getEntityRequired(Film.class, id);

        film.setName(put.getName());
        film.setInfo(put.getInfo());
        film.setAverageRating(put.getAverageRating());
        film.setRelease(put.getRelease());
        film.setTextMistake(put.getTextMistake());

        film = filmRepository.save(film);
        return toRead(film);
    }

    public void deleteFilm(UUID id) {
        filmRepository.delete(repositoryHelper.getEntityRequired(Film.class, id));
    }

    @Transactional
    public void updateAverageRatingOfFilm(UUID filmId) {
        Double averageRating = reviewRepository.calcAverageRatingOfFilm(filmId);
        Film film = filmRepository.findById(filmId).orElseThrow(
                () -> new EntityNotFoundException(Film.class, filmId));
        log.info("Setting new average rating of film: {}. Old value: {}, new value: {}",
                filmId, film.getAverageRating(), averageRating);
        film.setAverageRating(averageRating);
        filmRepository.save(film);
    }

    private FilmReadDTO toRead(Film film) {
        FilmReadDTO filmReadDTO = new FilmReadDTO();
        filmReadDTO.setId(film.getId());
        filmReadDTO.setCreatedAt(film.getCreatedAt());
        filmReadDTO.setUpdatedAt(film.getUpdatedAt());
        filmReadDTO.setName(film.getName());
        filmReadDTO.setInfo(film.getInfo());
        filmReadDTO.setAverageRating(film.getAverageRating());
        filmReadDTO.setTextMistake(film.getTextMistake());
        filmReadDTO.setRelease(film.getRelease());
        return filmReadDTO;
    }
}
