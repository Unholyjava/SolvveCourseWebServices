package com.coursesolvve.webproject.dto.news;

import lombok.Data;

import java.util.UUID;

@Data
public class NewsFilter {

    private UUID contentManagerId;

    private Boolean newsMistake;
    private Integer likeRatingFrom;
    private Integer likeRatingTo;
}
