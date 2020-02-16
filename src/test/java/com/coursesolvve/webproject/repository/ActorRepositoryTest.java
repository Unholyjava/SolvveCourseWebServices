package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Actor;
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
@Sql(statements = "delete from actor", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void testSave() {
        Actor c = new Actor();
        c = actorRepository.save(c);
        assertNotNull(c.getId());
        assertTrue(actorRepository.findById(c.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        Actor actor = createActor();

        Instant createdAtBeforeReload = actor.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        actor = actorRepository.findById(actor.getId()).get();

        Instant createdAtAfterReload = actor.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        Actor actor = createActor();

        Instant createdAt = actor.getCreatedAt();
        Instant updatedAtBeforeReload = actor.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        actor.setInfo("update_info_test");
        actor = actorRepository.save(actor);
        actor = actorRepository.findById(actor.getId()).get();
        Instant updatedAtAfterReload = actor.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
    }

    private Actor createActor() {
        Actor actor = new Actor();
        actor.setName("Actor_test1");
        actor.setPatronymic("Actor_Patronymic");
        actor.setSurname("Actor_Surname");
        actor.setInfo("This information is only for test");
        actor.setRatingFull(2.0);
        actor = actorRepository.save(actor);
        return actor;
    }
}
