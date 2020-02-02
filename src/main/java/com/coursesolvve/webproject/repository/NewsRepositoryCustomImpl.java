package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.News;
import com.coursesolvve.webproject.dto.news.NewsFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class NewsRepositoryCustomImpl implements NewsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> findByFilter(NewsFilter filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select n from News n where 1=1");
        if (filter.getContentManagerId() != null) {
            sb.append(" and n.contentManager.id = :contentManagerId");
        }
        if (filter.getNewsMistake() != null) {
            sb.append(" and n.newsMistake = :newsMistake");
        }
        if (filter.getLikeRatingFrom() != null) {
            sb.append(" and n.likeRating >= (:likeRatingFrom)");
        }
        if (filter.getLikeRatingTo() != null) {
            sb.append(" and n.likeRating < (:likeRatingTo)");
        }

        TypedQuery<News> query = entityManager.createQuery(sb.toString(), News.class);
        if (filter.getContentManagerId() != null) {
            query.setParameter("contentManagerId", filter.getContentManagerId());
        }
        if (filter.getNewsMistake() != null) {
            query.setParameter("newsMistake", filter.getNewsMistake());
        }
        if (filter.getLikeRatingFrom() != null) {
            query.setParameter("likeRatingFrom", filter.getLikeRatingFrom());
        }
        if (filter.getLikeRatingTo() != null) {
            query.setParameter("likeRatingTo", filter.getLikeRatingTo());
        }

        return query.getResultList();
    }
}
