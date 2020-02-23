package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.news.*;
import com.coursesolvve.webproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/content-managers/{content-managerId}/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/extended/{id}")
    public NewsReadExtendedDTO getNews(@PathVariable UUID id) {
        return newsService.getNews(id);
    }

    @GetMapping
    public List<NewsReadDTO> getNewsList(NewsFilter filter) {
        return newsService.getNewsList(filter);
    }

    @GetMapping("/{Id}")
    public List<NewsReadDTO> getContentManagerNews(@PathVariable UUID contentManagerId) {
        return newsService.getContentManagerNews(contentManagerId);
    }

    @PostMapping
    public NewsReadDTO createNews(@RequestBody UUID contentManagerId, NewsCreateDTO createDTO) {
        return newsService.createNews(contentManagerId, createDTO);
    }

    @PatchMapping("/{id}")
    public NewsReadDTO patchNews(@PathVariable UUID id, @RequestBody NewsPatchDTO patch) {
        return newsService.patchNews(id, patch);
    }

    @PutMapping("/{id}")
    public NewsReadDTO updateNews(@PathVariable UUID id, @RequestBody NewsPutDTO put) {
        return newsService.updateNews(id, put);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable UUID id) {
        newsService.deleteNews(id);
    }
}
