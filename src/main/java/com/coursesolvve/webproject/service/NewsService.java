package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.News;
import com.coursesolvve.webproject.dto.news.*;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TranslationService translationService;

    public NewsReadExtendedDTO getNews(UUID id) {
        News news = getNewsRequired(id);
        return translationService.toReadExtended(news);
    }

    public List<NewsReadDTO> getNewsList (NewsFilter filter) {
        List<News> newsList = newsRepository.findByFilter(filter);
        return newsList.stream().map(translationService::toRead).collect(Collectors.toList());
    }

    public List<NewsReadDTO> getContentManagerNews (UUID contentManagerId) {
        NewsFilter newsFilter = new NewsFilter();
        newsFilter.setId(contentManagerId);
        List<News> newsList = newsRepository.findByFilter(newsFilter);
        return newsList.stream().map(translationService::toRead).collect(Collectors.toList());
    }

    public NewsReadDTO createNews(NewsCreateDTO create) {
        News news = new News();
        news.setInfo(create.getInfo());
        news.setNewsMistake(create.getNewsMistake());
        news.setLikeRating(create.getLikeRating());
        news = newsRepository.save(news);
        return translationService.toRead(news);
    }

    public NewsReadDTO patchNews(UUID id, NewsPatchDTO patch) {
        News news = getNewsRequired(id);

        if (patch.getInfo() != null) {
            news.setInfo(patch.getInfo());
        }
        if (patch.getNewsMistake() != null) {
            news.setNewsMistake(patch.getNewsMistake());
        }
        if (patch.getLikeRating() != null) {
            news.setLikeRating(patch.getLikeRating());
        }

        news = newsRepository.save(news);
        return translationService.toRead(news);
    }

    public NewsReadDTO updateNews(UUID id, NewsPutDTO put) {
        News news = getNewsRequired(id);

        news.setInfo(put.getInfo());
        news.setNewsMistake(put.getNewsMistake());
        news.setLikeRating(put.getLikeRating());
        news = newsRepository.save(news);
        return translationService.toRead(news);
    }

    public void deleteNews(UUID id) {
        newsRepository.delete(getNewsRequired(id));
    }

    private News getNewsRequired(UUID id) {
        return newsRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(News.class, id);
        });
    }
}
