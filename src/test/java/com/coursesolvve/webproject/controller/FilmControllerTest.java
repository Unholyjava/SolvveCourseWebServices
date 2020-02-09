package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.dto.film.FilmCreateDTO;
import com.coursesolvve.webproject.dto.film.FilmPatchDTO;
import com.coursesolvve.webproject.dto.film.FilmPutDTO;
import com.coursesolvve.webproject.dto.film.FilmReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.FilmService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilmService filmService;

    @Test
    public void testGetFilm() throws Exception {
        FilmReadDTO read = createFilmRead();

        Mockito.when(filmService.getFilm(read.getId())).thenReturn(read);

        String resultJson = mvc.perform(get("/api/v1/films/{id}", read.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        FilmReadDTO actualFilm = objectMapper.readValue(resultJson, FilmReadDTO.class);
        Assertions.assertThat(actualFilm).isEqualToComparingFieldByField(read);

        Mockito.verify(filmService).getFilm(read.getId());
    }

    @Test
    public void testGetFilmWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Film.class, wrongId);
        Mockito.when(filmService.getFilm(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/films/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
        Assert.assertTrue(resultJson.contains(exception.getMessage()));
    }

    @Test
    public void testGetFilmWrongUuidFormat() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidFormat = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/films/{id}", wrongUuidFormat))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Assert.assertTrue(resultJson.contains("BAD_REQUEST"));
        System.out.println(resultJson);
    }

    @Test
    public void testCreateFilm() throws Exception {
        FilmCreateDTO create = new FilmCreateDTO();
        create.setName("Film_test2_create");
        create.setInfo("This information is only for test2_create");
        create.setRatingFull(10.0);
        create.setTextMistake(false);
        create.setRelease(true);

        FilmReadDTO read = createFilmRead();

        Mockito.when(filmService.createFilm(create)).thenReturn(read);
        String resultJson = mvc.perform(post("/api/v1/films")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        FilmReadDTO actualFilm = objectMapper.readValue(resultJson, FilmReadDTO.class);
        Assertions.assertThat(actualFilm).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchFilm() throws Exception {
        FilmPatchDTO patchDTO = new FilmPatchDTO();
        patchDTO.setName("Film_test2_create");
        patchDTO.setInfo("This information is only for test2_create");
        patchDTO.setRatingFull(10.0);
        patchDTO.setTextMistake(false);
        patchDTO.setRelease(true);

        FilmReadDTO read = createFilmRead();

        Mockito.when(filmService.patchFilm(read.getId(), patchDTO)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/films/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patchDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        FilmReadDTO actualFilm = objectMapper.readValue(resultJson, FilmReadDTO.class);
        Assert.assertEquals(read, actualFilm);
    }

    @Test
    public void testUpdateFilm() throws Exception {
        FilmPutDTO putDTO = new FilmPutDTO();
        putDTO.setName("Film_test2_create");
        putDTO.setInfo("This information is only for test2_create");
        putDTO.setRatingFull(10.0);
        putDTO.setTextMistake(false);
        putDTO.setRelease(true);

        FilmReadDTO read = createFilmRead();

        Mockito.when(filmService.updateFilm(read.getId(), putDTO)).thenReturn(read);

        String resultJson = mvc.perform(put("/api/v1/films/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(putDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        FilmReadDTO actualFilm = objectMapper.readValue(resultJson, FilmReadDTO.class);
        Assert.assertEquals(read, actualFilm);
    }

    @Test
    public void testDeleteFilm() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/films/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(filmService).deleteFilm(id);
    }

    private FilmReadDTO createFilmRead() {
        FilmReadDTO read = new FilmReadDTO();
        read.setId(UUID.randomUUID());
        read.setName("Film_test1");
        read.setInfo("This information is only for test");
        read.setRatingFull(10);
        read.setTextMistake(false);
        read.setRelease(true);
        return read;
    }
}
