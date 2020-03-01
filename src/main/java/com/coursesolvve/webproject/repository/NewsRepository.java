package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends CrudRepository<News, UUID>, NewsRepositoryCustom {

    List<News> findByContentManagerIdAndNewsMistakeOrderByLikeRatingAsc(UUID contentManagerId, Boolean newsMistake);

    @Query("select n from News n where n.contentManager.id = :contentManagerId "
            + "and n.newsMistake = :newsMistake and n.likeRating >= :from "
            + "and n.likeRating < :to order by n.likeRating asc")
    List<News> findNewsForContentManagerInGivenInterval(UUID contentManagerId,
                                                        Boolean newsMistake,
                                                        Integer from,
                                                        Integer to);
}
