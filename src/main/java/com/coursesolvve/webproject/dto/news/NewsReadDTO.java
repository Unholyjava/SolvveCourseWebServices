package com.coursesolvve.webproject.dto.news;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class NewsReadDTO {
    private UUID id;

    private UUID contentManagerId;

    private Instant createdAt;
    private Instant updatedAt;

    private String info;
    private Boolean newsMistake;
    private Integer likeRating;
}
