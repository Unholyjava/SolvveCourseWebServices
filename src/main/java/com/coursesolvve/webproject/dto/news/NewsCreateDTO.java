package com.coursesolvve.webproject.dto.news;

import lombok.Data;

@Data
public class NewsCreateDTO {

    private String info;
    private boolean newsMistake;
    private int likeRating;
}
