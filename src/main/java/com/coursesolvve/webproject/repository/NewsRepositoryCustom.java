package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.News;
import com.coursesolvve.webproject.dto.news.NewsFilter;

import java.util.List;

public interface NewsRepositoryCustom  {
    List<News> findByFilter(NewsFilter filter);
}
