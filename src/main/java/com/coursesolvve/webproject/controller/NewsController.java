package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.news.*;
import com.coursesolvve.webproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    public NewsReadExtendedDTO getNews(@PathVariable UUID id) {
        return newsService.getNews(id);
    }

    @GetMapping
    public List<NewsReadDTO> getNewsList(NewsFilter filter) {
        return newsService.getNewsList(filter);
    }

    @PostMapping
    public NewsReadDTO createNews(@RequestBody NewsCreateDTO createDTO) {
        return newsService.createNews(createDTO);
    }

    @PatchMapping("/{id}")
    public NewsReadDTO patchNews(@PathVariable UUID id, @RequestBody NewsPatchDTO patch) {
        return newsService.patchNews(id, patch);
    }

    @PutMapping("/{id}")
    public NewsReadDTO putNews(@PathVariable UUID id, @RequestBody NewsPutDTO put) {
        return newsService.putNews(id, put);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable UUID id) {
        newsService.deleteNews(id);
    }
}
