package com.coursesolvve.webproject.job;

import com.coursesolvve.webproject.repository.FilmRepository;
import com.coursesolvve.webproject.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UpdateAverageRatingOfFilmsJob {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    FilmService filmService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${update.average.rating.of.films.job.cron}")
    public void updateAverageRatingOfFilms() {
        log.info("Job started");

        filmRepository.getIdsOfFilms().forEach(filmId -> {
            try {
                filmService.updateAverageRatingOfFilm(filmId);
            } catch (Exception e) {
                log.error("Failed to update average rating for film: {}", filmId, e);
            }
        });

        log.info("Job finished");
    }
}
