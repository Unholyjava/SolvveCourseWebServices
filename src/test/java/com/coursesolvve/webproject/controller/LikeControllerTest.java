package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Like;
import com.coursesolvve.webproject.domain.LikedObjectType;
import com.coursesolvve.webproject.dto.like.LikeCreateDTO;
import com.coursesolvve.webproject.dto.like.LikePatchDTO;
import com.coursesolvve.webproject.dto.like.LikePutDTO;
import com.coursesolvve.webproject.dto.like.LikeReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.LikeService;
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
@WebMvcTest(LikeController.class)
public class LikeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LikeService likeService;

    @Test
    public void testGetLike() throws Exception {
        LikeReadDTO read = createLikeRead();

        Mockito.when(likeService.getLike(read.getId())).thenReturn(read);

        String resultJson = mvc.perform(get("/api/v1/likes/{id}", read.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        LikeReadDTO actualLike = objectMapper.readValue(resultJson, LikeReadDTO.class);
        Assertions.assertThat(actualLike).isEqualToComparingFieldByField(read);

        Mockito.verify(likeService).getLike(read.getId());
    }

    @Test
    public void testGetLikeWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Like.class, wrongId);
        Mockito.when(likeService.getLike(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/likes/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testGetLikeWrongUuidId() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/likes/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testCreateLike() throws Exception {
        LikeCreateDTO create = new LikeCreateDTO();
        create.setLike(Boolean.TRUE);
        create.setLikedObjectId(UUID.randomUUID());
        create.setLikeType(LikedObjectType.REVIEW);

        LikeReadDTO read = createLikeRead();

        Mockito.when(likeService.createLike(create)).thenReturn(read);
        String resultJson = mvc.perform(post("/api/v1/likes")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        LikeReadDTO actualLike = objectMapper.readValue(resultJson, LikeReadDTO.class);
        Assertions.assertThat(actualLike).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchLike() throws Exception {
        LikePatchDTO patchDTO = new LikePatchDTO();
        patchDTO.setLike(Boolean.TRUE);
        patchDTO.setLikedObjectId(UUID.randomUUID());
        patchDTO.setLikeType(LikedObjectType.REVIEW);

        LikeReadDTO read = createLikeRead();

        Mockito.when(likeService.patchLike(read.getId(), patchDTO)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/likes/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patchDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        LikeReadDTO actualLike = objectMapper.readValue(resultJson, LikeReadDTO.class);
        Assert.assertEquals(read, actualLike);
    }

    @Test
    public void testUpdateLike() throws Exception {
        LikePutDTO putDTO = new LikePutDTO();
        putDTO.setLike(Boolean.TRUE);
        putDTO.setLikedObjectId(UUID.randomUUID());
        putDTO.setLikeType(LikedObjectType.REVIEW);

        LikeReadDTO read = createLikeRead();

        Mockito.when(likeService.updateLike(read.getId(), putDTO)).thenReturn(read);

        String resultJson = mvc.perform(put("/api/v1/likes/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(putDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        LikeReadDTO actualLike = objectMapper.readValue(resultJson, LikeReadDTO.class);
        Assert.assertEquals(read, actualLike);
    }

    @Test
    public void testDeleteLike() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/likes/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(likeService).deleteLike(id);
    }

    private LikeReadDTO createLikeRead() {
        LikeReadDTO read = new LikeReadDTO();
        read.setId(UUID.randomUUID());
        read.setLike(Boolean.TRUE);
        read.setLikedObjectId(UUID.randomUUID());
        read.setLikeType(LikedObjectType.REVIEW);
        return read;
    }
}
