package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.ActorCreateDTO;
import com.coursesolvve.webproject.dto.ActorReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ActorRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = "delete from actor", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ActorServiceTest {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @Test
    public void testGetActor() {
        Actor actor = new Actor();
        actor.setName("Actor_test1");
        actor.setInfo("This information is only for test");
        actor.setRating(2);
        actor = actorRepository.save(actor);

        ActorReadDTO readDTO = actorService.getActor(actor.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(actor);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetActorWrongId() {
        actorService.getActor(UUID.randomUUID());
    }

    @Test
    public void testCreateActor() {
        ActorCreateDTO create = new ActorCreateDTO();
        create.setName("Actor_test2_create");
        create.setInfo("This information is only for test2_create");
        create.setRating(2);
        ActorReadDTO read = actorService.createActor(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Actor actor = actorRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(actor);
    }
}
