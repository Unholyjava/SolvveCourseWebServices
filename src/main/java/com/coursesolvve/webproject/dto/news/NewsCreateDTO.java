package com.coursesolvve.webproject.dto.news;

import lombok.Data;

import java.util.UUID;

@Data
public class NewsCreateDTO {
    private UUID contentManagerId;
    private String info;
    private Boolean newsMistake;
    private Integer likeRating;
}
