package com.coursesolvve.webproject.dto;

import lombok.Data;

@Data
public class NewsCreateDTO {
    private String info;
    private boolean newsMistake;
    private int likeRating;
}
