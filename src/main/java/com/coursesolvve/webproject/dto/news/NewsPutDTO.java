package com.coursesolvve.webproject.dto.news;

import lombok.Data;

@Data
public class NewsPutDTO {
    private String info;
    private Boolean newsMistake;
    private Integer likeRating;
}
