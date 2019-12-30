package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Film;
import com.coursesolvve.webproject.domain.Genres;
import com.coursesolvve.webproject.dto.FilmReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.FilmService;
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
@WebMvcTest(controllers = FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilmService filmService;

    @Test
    public void testGetFilm() throws Exception {
        FilmReadDTO film = new FilmReadDTO();
        film.setId(UUID.randomUUID());
        film.setName("Film_test1");
        film.setInfo("This information is only for test");
        film.setRatingFull(10);
        film.setTextMistake(false);
        film.setRelease(true);
        film.setGenres(Genres.ACTION);

        Mockito.when(filmService.getFilm(film.getId())).thenReturn(film);

        String resultJson = mvc.perform(get("/api/v1/films/{id}", film.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        FilmReadDTO actualFilm = objectMapper.readValue(resultJson, FilmReadDTO.class);
        Assertions.assertThat(actualFilm).isEqualToComparingFieldByField(film);

        Mockito.verify(filmService).getFilm(film.getId());
    }

    @Test
    public void testGetFilmWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Film.class, wrongId);
        Mockito.when(filmService.getFilm(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/films/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testGetFilmWrongUuidId() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/films/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }
}
