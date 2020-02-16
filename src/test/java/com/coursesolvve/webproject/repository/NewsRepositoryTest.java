package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.domain.News;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {
        "delete from news",
        "delete from content_manager"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Test
    public void testSave() {
        ContentManager contentManager = new ContentManager();
        contentManager = contentManagerRepository.save(contentManager);
        News c = new News();
        c.setContentManager(contentManager);
        c = newsRepository.save(c);
        assertNotNull(c.getId());
        assertTrue(newsRepository.findById(c.getId()).isPresent());
    }

    @Test
    public void testGetContentManagerNews() {
        ContentManager contentManager1 = createContentManager();
        ContentManager contentManager2 = createContentManager();
        News news1 = createNews(contentManager1, Boolean.TRUE, "info1", 11);
        createNews(contentManager1, Boolean.FALSE, "info11", 9);
        News news2 = createNews(contentManager1, Boolean.TRUE, "info2", 2);
        createNews(contentManager2, Boolean.TRUE, "info22", 4);
        News news3 = createNews(contentManager1, Boolean.TRUE, "info3", 5);

        List<News> result = newsRepository.findByContentManagerIdAndNewsMistakeOrderByLikeRatingAsc(contentManager1.getId(), Boolean.TRUE);
        Assertions.assertThat(result).extracting(News::getId).isEqualTo(Arrays.asList(news2.getId(), news3.getId(), news1.getId()));
    }

    @Test
    public void testGetContentManagerNewsInInterval() {
        ContentManager contentManager1 = createContentManager();
        ContentManager contentManager2 = createContentManager();
        News news1 = createNews(contentManager1, Boolean.TRUE, "info1", 11);
        createNews(contentManager1, Boolean.FALSE, "info11", 9);
        News news2 = createNews(contentManager1, Boolean.TRUE, "info2", 2);
        createNews(contentManager2, Boolean.TRUE, "info22", 4);
        News news3 = createNews(contentManager1, Boolean.TRUE, "info3", 5);

        List<News> result = newsRepository.findNewsForContentManagerInGivenInterval(
                contentManager1.getId(), Boolean.TRUE, 5, 12);
        Assertions.assertThat(result).extracting(News::getId).isEqualTo(Arrays.asList(news3.getId(), news1.getId()));
    }

    @Test
    public void testCreatedAtIsSet() {
        ContentManager contentManager = createContentManager();
        News news = new News();
        news.setContentManager(contentManager);
        news.setInfo("info test created data");
        news.setNewsMistake(Boolean.FALSE);
        news.setLikeRating(9);
        news = newsRepository.save(news);

        Instant createdAtBeforeReload = news.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        news = newsRepository.findById(news.getId()).get();

        Instant createdAtAfterReload = news.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        ContentManager contentManager = createContentManager();
        News news = new News();
        news.setContentManager(contentManager);
        news.setInfo("info test updated data");
        news.setNewsMistake(Boolean.FALSE);
        news.setLikeRating(9);
        news = newsRepository.save(news);

        Instant createdAt = news.getCreatedAt();
        Instant updatedAtBeforeReload = news.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        news.setNewsMistake(Boolean.TRUE);
        news = newsRepository.save(news);
        news = newsRepository.findById(news.getId()).get();
        Instant updatedAtAfterReload = news.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
    }

    private ContentManager createContentManager() {
        ContentManager entity = new ContentManager();
        entity.setNickName("Lenin");
        entity.setLogin("Login_test1");
        entity.setMail("qwer@ukr.net");
        entity.setName("Vladimir");
        entity.setPatronymic("Illich");
        entity.setSurname("Ulyanov");
        entity = contentManagerRepository.save(entity);
        return entity;
    }

    private News createNews(ContentManager c, Boolean newsMistake, String info, int rating) {
        News entity = new News();
        entity.setInfo(info);
        entity.setNewsMistake(newsMistake);
        entity.setLikeRating(rating);
        entity.setContentManager(c);
        entity = newsRepository.save(entity);
        return entity;
    }
}
