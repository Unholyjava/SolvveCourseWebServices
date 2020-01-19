package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.dto.FilmCreateDTO;
import com.coursesolvve.webproject.dto.FilmPatchDTO;
import com.coursesolvve.webproject.dto.FilmReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.FilmRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = "delete from film", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FilmServiceTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    @Test
    public void testGetFilm() {
        Film film = createFilm();

        FilmReadDTO readDTO = filmService.getFilm(film.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(film);
    }

    private Film createFilm() {
        Film film = new Film();
        film.setName("Film_test1");
        film.setInfo("This information is only for test");
        film.setRatingFull(10);
        film.setTextMistake(false);
        film.setRelease(true);
        film = filmRepository.save(film);
        return film;
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetFilmWrongId() {
        filmService.getFilm(UUID.randomUUID());
    }

    @Test
    public void testCreateFilm() {
        FilmCreateDTO create = new FilmCreateDTO();
        create.setName("Film_test2_create");
        create.setInfo("This information is only for test_create");
        create.setRatingFull(10);
        create.setTextMistake(false);
        create.setRelease(true);
        FilmReadDTO read = filmService.createFilm(create);

        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Film film = filmRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(film);
    }

    @Test
    public void testPatchFilm() {
        Film film = createFilm();

        FilmPatchDTO patch = new FilmPatchDTO();
        patch.setName("Film_test2_create");
        patch.setInfo("This information is only for test_create");
        patch.setRatingFull(10);
        patch.setTextMistake(false);
        patch.setRelease(true);
        FilmReadDTO read = filmService.patchFilm(film.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        film = filmRepository.findById(read.getId()).get();
        Assertions.assertThat(film).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchFilmEmptyPatch() {
        Film film = createFilm();

        FilmPatchDTO patch = new FilmPatchDTO();
        FilmReadDTO read = filmService.patchFilm(film.getId(), patch);

        Assert.assertNotNull(read.getName());
        Assert.assertNotNull(read.getInfo());

        Film filmAfterUpdate = filmRepository.findById(read.getId()).get();

        Assert.assertNotNull(filmAfterUpdate.getName());
        Assert.assertNotNull(filmAfterUpdate.getInfo());

        Assertions.assertThat(film).isEqualToIgnoringGivenFields(filmAfterUpdate,
                "ratingFull", "textMistake", "release");
    }

    @Test
    public void testDeleteFilm() {
        Film film = createFilm();

        filmService.deleteFilm(film.getId());
        Assert.assertFalse(filmRepository.existsById(film.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteFilmNotFound() {
        filmService.deleteFilm(UUID.randomUUID());
    }
}
