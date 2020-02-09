package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.actor.ActorCreateDTO;
import com.coursesolvve.webproject.dto.actor.ActorPatchDTO;
import com.coursesolvve.webproject.dto.actor.ActorPutDTO;
import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
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
        Actor actor = createActor();

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
        create.setPatronymic("Actor_Patronymic");
        create.setSurname("Actor_Surname");
        create.setInfo("This information is only for test2_create");
        create.setRatingFull(2.0);
        ActorReadDTO read = actorService.createActor(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Actor actor = actorRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(actor);
    }

    @Test
    public void testPatchActor() {
        Actor actor = createActor();

        ActorPatchDTO patch = new ActorPatchDTO();
        patch.setName("Actor_test2_patch");
        patch.setPatronymic("Actor_Patronymic");
        patch.setSurname("Actor_Surname");
        patch.setInfo("This information is only for test2");
        patch.setRatingFull(2.0);
        ActorReadDTO read = actorService.patchActor(actor.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        actor = actorRepository.findById(read.getId()).get();
        Assertions.assertThat(actor).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchActorEmptyPatch() {
        Actor actor = createActor();
        ActorPatchDTO patch = new ActorPatchDTO();
        ActorReadDTO read = actorService.patchActor(actor.getId(), patch);
        Assertions.assertThat(read).hasNoNullFieldsOrProperties();

        Actor actorAfterUpdate = actorRepository.findById(read.getId()).get();
        Assertions.assertThat(actorAfterUpdate).hasNoNullFieldsOrProperties();

        Assertions.assertThat(actor).isEqualToComparingFieldByField(actorAfterUpdate);
    }

    @Test
    public void testUpdateActor() {
        Actor actor = createActor();

        ActorPutDTO put = new ActorPutDTO();
        put.setName("Actor_test2_put");
        put.setPatronymic("Actor_Patronymic");
        put.setSurname("Actor_Surname");
        put.setInfo("This information is only for test2");
        put.setRatingFull(2.0);
        ActorReadDTO read = actorService.updateActor(actor.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        actor = actorRepository.findById(read.getId()).get();
        Assertions.assertThat(actor).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testDeleteActor() {
        Actor actor = createActor();

        actorService.deleteActor(actor.getId());
        Assert.assertFalse(actorRepository.existsById(actor.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteActorNotFound() {
        actorService.deleteActor(UUID.randomUUID());
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
