package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from actor", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class RepositoryHelperTest {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RepositoryHelper repositoryHelper;

    @Test
    public void testGetReferenceIfExistTrue() {
        Actor existActor = new Actor();
        existActor = actorRepository.save(existActor);
        Actor expectedExistActor = repositoryHelper.getReferenceIfExist(
                Actor.class, existActor.getId());
        assertTrue(actorRepository.findById(existActor.getId()).isPresent());
        assertEquals(existActor.getId(), expectedExistActor.getId());
        Assertions.assertThat(existActor).isNotEqualTo(expectedExistActor);
    }

    @Test (expected = EntityNotFoundException.class)
    public void testGetReferenceIfExistFalse() {
        repositoryHelper.getReferenceIfExist(Actor.class, UUID.randomUUID());
    }

    @Test
    public void testGetEntityRequiredTrue() {
        Actor existActor = new Actor();
        existActor = actorRepository.save(existActor);
        Actor expectedExistActor = repositoryHelper.getEntityRequired(
                Actor.class, existActor.getId());
        assertTrue(actorRepository.findById(existActor.getId()).isPresent());
        Assertions.assertThat(existActor).isEqualToComparingFieldByField(expectedExistActor);
        assertEquals(false, existActor.equals(expectedExistActor));
    }

    @Test (expected = EntityNotFoundException.class)
    public void testGetEntityRequiredFalse() {
        repositoryHelper.getEntityRequired(Actor.class, UUID.randomUUID());
    }
}
