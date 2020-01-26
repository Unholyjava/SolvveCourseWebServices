package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.actor.ActorCreateDTO;
import com.coursesolvve.webproject.dto.actor.ActorPatchDTO;
import com.coursesolvve.webproject.dto.actor.ActorPutDTO;
import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.ActorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ActorController.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActorService actorService;

    @Test
    public void testGetActor() throws Exception {
        ActorReadDTO read = createActorRead();

        Mockito.when(actorService.getActor(read.getId())).thenReturn(read);

        String resultJson = mvc.perform(get("/api/v1/actors/{id}", read.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        ActorReadDTO actualActor = objectMapper.readValue(resultJson, ActorReadDTO.class);
        Assertions.assertThat(actualActor).isEqualToComparingFieldByField(read);

        Mockito.verify(actorService).getActor(read.getId());
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
    public void testGetActorWrongUuidFormat() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/actors/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testCreateActor() throws Exception {
        ActorCreateDTO create = new ActorCreateDTO();
        create.setName("Actor_test2_create");
        create.setPatronymic("Actor_Patronymic");
        create.setSurname("Actor_Surname");
        create.setInfo("This information is only for test2_create");
        create.setRatingFull(3);

        ActorReadDTO read = createActorRead();

        Mockito.when(actorService.createActor(create)).thenReturn(read);
        String resultJson = mvc.perform(post("/api/v1/actors")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ActorReadDTO actualActor = objectMapper.readValue(resultJson, ActorReadDTO.class);
        Assertions.assertThat(actualActor).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchActor() throws Exception {
        ActorPatchDTO patchDTO = new ActorPatchDTO();
        patchDTO.setName("Actor_test2_create");
        patchDTO.setPatronymic("Actor_Patronymic");
        patchDTO.setSurname("Actor_Surname");
        patchDTO.setInfo("This information is only for test2_create");
        patchDTO.setRatingFull(3.0);

        ActorReadDTO read = createActorRead();

        Mockito.when(actorService.patchActor(read.getId(), patchDTO)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/actors/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patchDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ActorReadDTO actualActor = objectMapper.readValue(resultJson, ActorReadDTO.class);
        Assert.assertEquals(read, actualActor);
    }

    @Test
    public void testPutActor() throws Exception {
        ActorPutDTO putDTO = new ActorPutDTO();
        putDTO.setName("Actor_test2_create");
        putDTO.setPatronymic("Actor_Patronymic");
        putDTO.setSurname("Actor_Surname");
        putDTO.setInfo("This information is only for test2_create");
        putDTO.setRatingFull(3.0);

        ActorReadDTO read = createActorRead();

        Mockito.when(actorService.putActor(read.getId(), putDTO)).thenReturn(read);

        String resultJson = mvc.perform(put("/api/v1/actors/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(putDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ActorReadDTO actualActor = objectMapper.readValue(resultJson, ActorReadDTO.class);
        Assert.assertEquals(read, actualActor);
    }

    @Test
    public void testDeleteActor() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/actors/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(actorService).deleteActor(id);
    }

    private ActorReadDTO createActorRead() {
        ActorReadDTO read = new ActorReadDTO();
        read.setId(UUID.randomUUID());
        read.setName("Actor_test1");
        read.setPatronymic("Actor_Patronymic");
        read.setSurname("Actor_Surname");
        read.setInfo("This information is only for test");
        read.setRatingFull(3);
        return read;
    }
}
