package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.domain.Review;
import com.coursesolvve.webproject.domain.ReviewStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {
        "delete from client",
        "delete from review"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void testSave() {
        Client client = new Client();
        client = clientRepository.save(client);
        Review r = new Review();
        r.setClient(client);
        r = reviewRepository.save(r);
        assertNotNull(r.getId());
        assertTrue(reviewRepository.findById(r.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        Client client = createClient();
        Review review = new Review();
        review.setClient(client);
        review.setReviewStatus(ReviewStatus.NEW);
        review.setFilmReview("film-review_test1");
        review.setFilmRating(11);
        review.setRoleReview("role-review_test1");
        review.setReviewMistake(Boolean.FALSE);
        review.setLikeRating(9);
        review.setDataReview(Instant.now());

        review = reviewRepository.save(review);

        Instant createdAtBeforeReload = review.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        review = reviewRepository.findById(review.getId()).get();

        Instant createdAtAfterReload = review.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        Client client = createClient();
        Review review = new Review();
        review.setClient(client);
        review.setReviewStatus(ReviewStatus.NEW);
        review.setFilmReview("film-review_test1");
        review.setFilmRating(11);
        review.setRoleReview("role-review_test1");
        review.setReviewMistake(Boolean.FALSE);
        review.setLikeRating(9);
        review.setDataReview(Instant.now());

        review = reviewRepository.save(review);

        Instant createdAt = review.getCreatedAt();
        Instant updatedAtBeforeReload = review.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        review.setReviewStatus(ReviewStatus.MODERATED);
        review = reviewRepository.save(review);
        review = reviewRepository.findById(review.getId()).get();
        Instant updatedAtAfterReload = review.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
    }

    @Test
    public void testCalcAverageRating() {
        Client client = createClient();
        Film film1 = createFilm();
        Film film2 = createFilm();

        createReview(client, film1, 4);
        createReview(client, film1, 9);
        createReview(client, film1, (Integer)null);
        createReview(client, film2, 3);

        Assert.assertEquals(6.5, reviewRepository.calcAverageRatingOfFilm(film1.getId()), Double.MIN_NORMAL);
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
