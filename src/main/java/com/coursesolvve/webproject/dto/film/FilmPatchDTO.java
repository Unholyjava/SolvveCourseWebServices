package com.coursesolvve.webproject.dto.film;

import lombok.Data;

@Data
public class FilmPatchDTO {
    private String name;
    private String info;
    private Double averageRating;
    private Boolean textMistake;
    private Boolean release;
}
