package com.coursesolvve.webproject.job;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.domain.Review;
import com.coursesolvve.webproject.domain.ReviewStatus;
import com.coursesolvve.webproject.repository.ClientRepository;
import com.coursesolvve.webproject.repository.FilmRepository;
import com.coursesolvve.webproject.repository.ReviewRepository;
import com.coursesolvve.webproject.service.FilmService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UpdateAverageRatingOfFilmsJobTest {

    @Autowired
    private UpdateAverageRatingOfFilmsJob updateAverageRatingOfFilmsJob;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @SpyBean
    private FilmService filmService;

    @Test
    public void testUpdateAverageRatingOfFilms() {
        Client client = createClient();
        Film film1 = createFilm();

        createReview(client, film1, 4);
        createReview(client, film1, 9);

        updateAverageRatingOfFilmsJob.updateAverageRatingOfFilms();
        film1 = filmRepository.findById(film1.getId()).get();
        Assert.assertEquals(6.5, film1.getAverageRating(), Double.MIN_NORMAL);

    }

    @Ignore
    @Test
    public void testFilmsUpdatedIndependently() {
        Client client = createClient();
        Film film1 = createFilm();
        Film film2 = createFilm();

        createReview(client, film1, 4);
        createReview(client, film2, 9);

        UUID[] failedId = new UUID[1];
        Mockito.doAnswer(invocationOnMock -> {
            if (failedId[0] == null) {
                failedId[0] = invocationOnMock.getArgument(0);
                throw new RuntimeException();
            }
            return invocationOnMock.callRealMethod();
        }).when(filmService).updateAverageRatingOfFilm(Mockito.any());

        updateAverageRatingOfFilmsJob.updateAverageRatingOfFilms();

        for (Film f : filmRepository.findAll()) {
            if (f.getId().equals(failedId[0])) {
                Assert.assertNull(f.getAverageRating());
            } else {
                Assert.assertNotNull(f.getAverageRating());
            }
        }
    }

    private Film createFilm() {
        Film film = new Film();
        film.setName("Film_test1");
        film.setInfo("This information is only for test");
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
