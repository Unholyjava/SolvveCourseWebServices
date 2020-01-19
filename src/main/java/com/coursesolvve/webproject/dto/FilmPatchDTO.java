package com.coursesolvve.webproject.dto;

import lombok.Data;

@Data
public class FilmPatchDTO {
    private String name;
    private String info;
    private double ratingFull;
    private boolean textMistake;
    private boolean release;
}
