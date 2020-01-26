package com.coursesolvve.webproject.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NewsReadDTO {
    private UUID id;

    private UUID contentManagerId;

    private String info;
    private boolean newsMistake;
    private int likeRating;
}
