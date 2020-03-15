package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.domain.Review;
import com.coursesolvve.webproject.domain.ReviewStatus;
import com.coursesolvve.webproject.dto.film.FilmCreateDTO;
import com.coursesolvve.webproject.dto.film.FilmPatchDTO;
import com.coursesolvve.webproject.dto.film.FilmPutDTO;
import com.coursesolvve.webproject.dto.film.FilmReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ClientRepository;
import com.coursesolvve.webproject.repository.FilmRepository;
import com.coursesolvve.webproject.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {
        "delete from client",
        "delete from review",
        "delete from film"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FilmServiceTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testGetFilm() {
        Film film = createFilm();

        FilmReadDTO readDTO = filmService.getFilm(film.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(film);
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
        create.setAverageRating(10.0);
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
        patch.setAverageRating(10.0);
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
        Assertions.assertThat(read).hasNoNullFieldsOrProperties();

        Film filmAfterUpdate = filmRepository.findById(read.getId()).get();
        Assertions.assertThat(filmAfterUpdate).hasNoNullFieldsOrProperties();

        Assertions.assertThat(film).isEqualToComparingFieldByField(filmAfterUpdate);
    }

    @Test
    public void testUpdateFilm() {
        Film film = createFilm();

        FilmPutDTO put = new FilmPutDTO();
        put.setName("Film_test2_create");
        put.setInfo("This information is only for test_create");
        put.setAverageRating(10.0);
        put.setTextMistake(false);
        put.setRelease(true);
        FilmReadDTO read = filmService.updateFilm(film.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        film = filmRepository.findById(read.getId()).get();
        Assertions.assertThat(film).isEqualToComparingFieldByField(read);
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

    @Test
    public void testUpdateAverageRatingOfFilm() {
        Client client = createClient();
        Film film1 = createFilm();

        createReview(client, film1, 4);
        createReview(client, film1, 9);

        filmService.updateAverageRatingOfFilm(film1.getId());
        film1 = filmRepository.findById(film1.getId()).get();
        Assert.assertEquals(6.5, film1.getAverageRating(), Double.MIN_NORMAL);
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

    private Client createClient() {
        Client client = new Client();
        client.setNickName("Client_test1");
        client.setLogin("ClientLogin_test1");
        client.setMail("test_mail");
        client.setName("test_Name");
        client.setPatronymic("test_Patronymic");
        client.setSurname("test_Surname");
        client.setTrust(true);
        client.setReviewRating(6);
        client.setActiveRating(7);
        client.setIsBlock(false);
        client = clientRepository.save(client);
        return client;
    }

    private Review createReview(Client client, Film film, Integer filmRating) {
        Review review = new Review();
        review.setClient(client);
        review.setFilm(film);
        review.setFilmRating(filmRating);
        review.setReviewStatus(ReviewStatus.NEW);
        review.setFilmReview("film-review_test1");
        review.setRoleReview("role-review_test1");
        review.setReviewMistake(Boolean.FALSE);
        review.setLikeRating(9);
        review.setDataReview(Instant.now());
        review = reviewRepository.save(review);
        return review;
    }
}
