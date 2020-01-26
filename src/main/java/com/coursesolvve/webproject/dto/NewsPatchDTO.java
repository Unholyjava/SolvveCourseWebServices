package com.coursesolvve.webproject.dto;

import lombok.Data;

@Data
public class NewsPatchDTO {
    private String info;
    private Boolean newsMistake;
    private Integer likeRating;
}
