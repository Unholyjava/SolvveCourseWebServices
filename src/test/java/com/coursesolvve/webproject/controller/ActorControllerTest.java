package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.ActorReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.ActorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ActorController.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActorService actorService;

    @Test
    public void testGetActor() throws Exception {
        ActorReadDTO actor = new ActorReadDTO();
        actor.setId(UUID.randomUUID());
        actor.setName("Actor_test1");
        actor.setInfo("This information is only for test");
        actor.setRating(3);

        Mockito.when(actorService.getActor(actor.getId())).thenReturn(actor);

        String resultJson = mvc.perform(get("/api/v1/actors/{id}", actor.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        ActorReadDTO actualActor = objectMapper.readValue(resultJson, ActorReadDTO.class);
        Assertions.assertThat(actualActor).isEqualToComparingFieldByField(actor);

        Mockito.verify(actorService).getActor(actor.getId());
    }

    @Test
    public void testGetActorWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Actor.class, wrongId);
        Mockito.when(actorService.getActor(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/actors/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testGetActorWrongUuidId() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/actors/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }
}
