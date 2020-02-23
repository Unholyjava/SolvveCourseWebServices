package com.coursesolvve.webproject.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml")
@Sql(statements = {
        "delete from news",
        "delete from content_manager",
        "delete from genre",
        "delete from film",
        "delete from client",
        "delete from maker",
        "delete from role",
        "delete from actor",
        "delete from review"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LiquibaseLoadDataTest {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MakerRepository makerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    public void testDataLoaded() {
        Assert.assertTrue(contentManagerRepository.count() > 0);
        Assert.assertTrue(newsRepository.count() > 0);
        Assert.assertTrue(genreRepository.count() > 0);
        Assert.assertTrue(filmRepository.count() > 0);
        Assert.assertTrue(actorRepository.count() > 0);
        Assert.assertTrue(clientRepository.count() > 0);
        Assert.assertTrue(makerRepository.count() > 0);
        Assert.assertTrue(roleRepository.count() > 0);
        Assert.assertTrue(reviewRepository.count() > 0);
    }
}
