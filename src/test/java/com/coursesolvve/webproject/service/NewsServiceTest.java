package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.domain.News;
import com.coursesolvve.webproject.dto.news.*;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ContentManagerRepository;
import com.coursesolvve.webproject.repository.NewsRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {
        "delete from news",
        "delete from content_manager"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class NewsServiceTest {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void testGetNewsExtended() {
        ContentManager contentManager = createContentManager();
        News news = createNews(contentManager);
        NewsReadExtendedDTO read = newsService.getNews(news.getId());
        Assertions.assertThat(read).isEqualToIgnoringGivenFields(news, "contentManager");
        Assertions.assertThat(read.getContentManager()).isEqualToIgnoringGivenFields(contentManager);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetNewsWrongId() {
        newsService.getNews(UUID.randomUUID());
    }

    @Test
    public void testGetNewsWithEmptyFilter() {
        ContentManager cm1 = createContentManager();
        ContentManager cm2 = createContentManager();
        News n1 = createNews(cm1);
        News n2 = createNews(cm2);
        News n3 = createNews(cm1);

        NewsFilter filter = new NewsFilter();
        Assertions.assertThat(newsService.getListOfNews(filter)).extracting("id")
                .containsExactlyInAnyOrder(n1.getId(), n2.getId(), n3.getId());
    }

    @Test
    public void testGetNewsByContentManager() {
        ContentManager cm1 = createContentManager();
        ContentManager cm2 = createContentManager();
        News n1 = createNews(cm1);
        createNews(cm2);
        News n3 = createNews(cm1);

        NewsFilter filter = new NewsFilter();
        filter.setContentManagerId(cm1.getId());
        Assertions.assertThat(newsService.getListOfNews(filter)).extracting("id")
                .containsExactlyInAnyOrder(n1.getId(), n3.getId());
    }

    @Test
    public void testGetNewsByNewsMistake() {
        ContentManager cm1 = createContentManager();
        ContentManager cm2 = createContentManager();
        News n1 = createNews(cm1, Boolean.TRUE);
        createNews(cm1);
        News n3 = createNews(cm2, Boolean.TRUE);

        NewsFilter filter = new NewsFilter();
        filter.setNewsMistake(Boolean.TRUE);
        Assertions.assertThat(newsService.getListOfNews(filter)).extracting("id")
                .containsExactlyInAnyOrder(n1.getId(), n3.getId());
    }

    @Test
    public void testGetNewsByLikeRatingInterval() {
        ContentManager cm1 = createContentManager();
        ContentManager cm2 = createContentManager();
        News n1 = createNews(cm1, 5);
        createNews(cm1, 3);
        News n3 = createNews(cm2, 12);

        NewsFilter filter = new NewsFilter();
        filter.setLikeRatingFrom(5);
        filter.setLikeRatingTo(13);
        Assertions.assertThat(newsService.getListOfNews(filter)).extracting("id")
                .containsExactlyInAnyOrder(n1.getId(), n3.getId());
    }

    @Test
    public void testGetNewsByAllFilters() {
        ContentManager cm1 = createContentManager();
        ContentManager cm2 = createContentManager();
        createNews(cm1, Boolean.FALSE);
        createNews(cm2, 7);
        News n3 = createNews(cm1, 12);

        NewsFilter filter = new NewsFilter();
        filter.setContentManagerId(cm1.getId());
        filter.setNewsMistake(Boolean.TRUE);
        filter.setLikeRatingFrom(5);
        filter.setLikeRatingTo(13);
        Assertions.assertThat(newsService.getListOfNews(filter)).extracting("id")
                .containsExactlyInAnyOrder(n3.getId());
    }

    @Test
    public void testCreateNews() {
        ContentManager contentManager = createContentManager();
        NewsCreateDTO create = new NewsCreateDTO();
        create.setContentManagerId(contentManager.getId());
        create.setInfo("News_test2_create");
        create.setNewsMistake(false);
        create.setLikeRating(4);
        NewsReadDTO read = newsService.createNews(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());
        News news = newsRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToIgnoringGivenFields(news,
                "contentManagerId");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCreateNewsWithWrongContentManager() {
        NewsCreateDTO create = new NewsCreateDTO();
        create.setContentManagerId(UUID.randomUUID());
        create.setInfo("News_test2_create");
        create.setNewsMistake(false);
        create.setLikeRating(4);

        newsService.createNews(create);
    }

    @Test
    public void testPatchNews() {
        News news = createNews();
        NewsPatchDTO patch = new NewsPatchDTO();
        patch.setInfo("News_test2_create");
        patch.setNewsMistake(false);
        patch.setLikeRating(4);
        NewsReadDTO read = newsService.patchNews(news.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        news = newsRepository.findById(read.getId()).get();
        Assertions.assertThat(news).isEqualToIgnoringGivenFields(read,
                "contentManager");
    }

    @Test
    public void testPatchNewsEmptyPatch() {
        News news = createNews();
        NewsPatchDTO patch = new NewsPatchDTO();
        NewsReadDTO read = newsService.patchNews(news.getId(), patch);
        Assertions.assertThat(read).hasNoNullFieldsOrPropertiesExcept("contentManagerId");

        News newsAfterUpdate = newsRepository.findById(read.getId()).get();
        Assertions.assertThat(newsAfterUpdate).hasNoNullFieldsOrPropertiesExcept("contentManager");

        Assertions.assertThat(news).isEqualToIgnoringGivenFields(newsAfterUpdate,
                "contentManager");
    }

    @Test
    public void testUpdateNews() {
        News news = createNews();
        NewsPutDTO put = new NewsPutDTO();
        put.setInfo("News_test2_create");
        put.setNewsMistake(false);
        put.setLikeRating(4);
        NewsReadDTO read = newsService.updateNews(news.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        news = newsRepository.findById(read.getId()).get();
        Assertions.assertThat(news).isEqualToIgnoringGivenFields(read, "contentManager");
    }

    @Test
    public void testDeleteNews() {
        News news = createNews(createContentManager());

        newsService.deleteNews(news.getId());
        Assert.assertFalse(newsRepository.existsById(news.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteNewsNotFound() {
        newsService.deleteNews(UUID.randomUUID());
    }

    private News createNews(ContentManager contentManager) {
        News news = new News();
        news.setInfo("News_test1");
        news.setNewsMistake(false);
        news.setLikeRating(4);
        news.setContentManager(contentManager);
        news = newsRepository.save(news);
        return news;
    }

    private News createNews(ContentManager contentManager, Boolean newsMistake) {
        News news = new News();
        news.setInfo("News_test1");
        news.setNewsMistake(newsMistake);
        news.setLikeRating(4);
        news.setContentManager(contentManager);
        news = newsRepository.save(news);
        return news;
    }

    private News createNews(ContentManager contentManager, int rating) {
        News news = new News();
        news.setInfo("News_test1");
        news.setNewsMistake(true);
        news.setLikeRating(rating);
        news.setContentManager(contentManager);
        news = newsRepository.save(news);
        return news;
    }

    private News createNews() {
        ContentManager contentManager = new ContentManager();
        contentManagerRepository.save(contentManager);
        News news = new News();
        news.setContentManager(contentManager);
        news.setInfo("News_test1");
        news.setNewsMistake(false);
        news.setLikeRating(4);
        news = newsRepository.save(news);
        return news;
    }

    private ContentManager createContentManager() {
        ContentManager contentManager = new ContentManager();
        contentManager.setNickName("ContentManager_Nik_test1");
        contentManager.setLogin("Login_test1");
        contentManager.setMail("Mail_test1");
        contentManager.setName("ContentManager_test1");
        contentManager.setPatronymic("Patronymic_test1");
        contentManager.setSurname("Surname_test1");
        contentManager = contentManagerRepository.save(contentManager);
        return contentManager;
    }
}
