package com.coursesolvve.webproject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class FilmReadDTO {
    private UUID id;
    private String name;
    private String info;
    private double ratingFull;
    private boolean textMistake;
    private boolean release;
}
