package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.ContentManager;
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
@Sql(statements = "delete from content_manager", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class ContentManagerRepositoryTest {

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Test
    public void testSave() {
        ContentManager c = new ContentManager();
        c = contentManagerRepository.save(c);
        assertNotNull(c.getId());
        assertTrue(contentManagerRepository.findById(c.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        ContentManager contentManager = createContentManager();

        Instant createdAtBeforeReload = contentManager.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        contentManager = contentManagerRepository.findById(contentManager.getId()).get();

        Instant createdAtAfterReload = contentManager.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        ContentManager contentManager = createContentManager();

        Instant createdAt = contentManager.getCreatedAt();
        Instant updatedAtBeforeReload = contentManager.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        contentManager.setMail("qwer@gmail.com");
        contentManager = contentManagerRepository.save(contentManager);
        contentManager = contentManagerRepository.findById(contentManager.getId()).get();
        Instant updatedAtAfterReload = contentManager.getUpdatedAt();
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
}
