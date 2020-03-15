package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Film;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from film", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void testSave() {
        Film c = new Film();
        c = filmRepository.save(c);
        assertNotNull(c.getId());
        assertTrue(filmRepository.findById(c.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        Film film = createFilm();

        Instant createdAtBeforeReload = film.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        film = filmRepository.findById(film.getId()).get();

        Instant createdAtAfterReload = film.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        Film film = createFilm();

        Instant createdAt = film.getCreatedAt();
        Instant updatedAtBeforeReload = film.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        film.setInfo("test update info");
        film = filmRepository.save(film);
        film = filmRepository.findById(film.getId()).get();
        Instant updatedAtAfterReload = film.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
    }

    @Test
    public void testGetIdsOfFilms() {
        Set<UUID> expectedIdsOfFilms = new HashSet<>();
        expectedIdsOfFilms.add(createFilm().getId());
        expectedIdsOfFilms.add(createFilm().getId());

        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Assert.assertEquals(expectedIdsOfFilms, filmRepository.getIdsOfFilms().collect(Collectors.toSet()));
        });
    }

    private Film createFilm() {
        Film film = new Film();
        film.setName("Film_test1");
        film.setInfo("This information is only for test");
        film.setAverageRating(10.0);
        film.setTextMistake(false);
        film.setRelease(true);
        film = filmRepository.save(film);
        return film;
    }
}
