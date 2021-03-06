package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.domain.News;
import com.coursesolvve.webproject.dto.news.*;
import com.coursesolvve.webproject.repository.NewsRepository;
import com.coursesolvve.webproject.repository.RepositoryHelper;
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

    @Autowired
    private RepositoryHelper repositoryHelper;

    public NewsReadExtendedDTO getNews(UUID id) {
        News news = repositoryHelper.getEntityRequired(News.class, id);
        return translationService.toReadExtended(news);
    }

    public List<NewsReadDTO> getListOfNews(NewsFilter filter) {
        List<News> news = newsRepository.findByFilter(filter);
        return news.stream().map(translationService::toRead).collect(Collectors.toList());
    }

    public List<NewsReadDTO> getContentManagerNews(UUID contentManagerId) {
        NewsFilter newsFilter = new NewsFilter();
        newsFilter.setContentManagerId(contentManagerId);
        List<News> news = newsRepository.findByFilter(newsFilter);
        return news.stream().map(translationService::toRead).collect(Collectors.toList());
    }

    public NewsReadDTO createNews(NewsCreateDTO create) {
        News news = translationService.toEntity(create);
        news = newsRepository.save(news);
        return translationService.toRead(news);
    }

    public NewsReadDTO createNews(UUID contentManagerId, NewsCreateDTO create) {
        News news = translationService.toEntity(create);
        news.setContentManager(repositoryHelper.getReferenceIfExist(
                ContentManager.class, contentManagerId));
        news = newsRepository.save(news);
        return translationService.toRead(news);
    }

    public NewsReadDTO patchNews(UUID id, NewsPatchDTO patch) {
        News news = repositoryHelper.getEntityRequired(News.class, id);

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
        News news = repositoryHelper.getEntityRequired(News.class, id);

        news.setInfo(put.getInfo());
        news.setNewsMistake(put.getNewsMistake());
        news.setLikeRating(put.getLikeRating());
        news = newsRepository.save(news);
        return translationService.toRead(news);
    }

    public void deleteNews(UUID id) {
        newsRepository.delete(repositoryHelper.getEntityRequired(News.class, id));
    }
}
