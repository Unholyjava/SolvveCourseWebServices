package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.news.NewsFilter;
import com.coursesolvve.webproject.dto.news.NewsReadDTO;
import com.coursesolvve.webproject.service.NewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NewsService newsService;

    @Test
    @Ignore
    public void testGetNews() throws Exception {
        NewsFilter newsFilter = new NewsFilter();
        newsFilter.setContentManagerId(UUID.randomUUID());
        newsFilter.setNewsMistake(Boolean.TRUE);
        newsFilter.setLikeRatingFrom(Integer.MIN_VALUE);
        newsFilter.setLikeRatingTo(Integer.MAX_VALUE);

        NewsReadDTO read = new NewsReadDTO();
        read.setContentManagerId(newsFilter.getContentManagerId());
        read.setNewsMistake(Boolean.TRUE);
        read.setLikeRating(Integer.MIN_VALUE);
        read.setId(UUID.randomUUID());
        read.setInfo("test");
        List<NewsReadDTO> expectedResult = List.of(read);
        Mockito.when(newsService.getListOfNews(newsFilter)).thenReturn(expectedResult);

        String resultJson = mvc.perform(get("/api/v1/content-managers/{content-managerId}")
                .param("contentManagerId", newsFilter.getContentManagerId().toString())
                .param("newsMistake", newsFilter.getNewsMistake().toString())
                .param("likeRatingFrom", newsFilter.getLikeRatingFrom().toString())
                .param("likeRatingTo", newsFilter.getLikeRatingTo().toString()))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<NewsReadDTO> actualResult = objectMapper.readValue(resultJson, new TypeReference<>() {});
        Assert.assertEquals(expectedResult, actualResult);
    }
}
