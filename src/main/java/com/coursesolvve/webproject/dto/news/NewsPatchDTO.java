package com.coursesolvve.webproject.dto.news;

import lombok.Data;

@Data
public class NewsPatchDTO {
    private String info;
    private Boolean newsMistake;
    private Integer likeRating;
}
