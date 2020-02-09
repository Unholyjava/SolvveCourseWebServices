package com.coursesolvve.webproject.dto.film;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class FilmCreateDTO {
    private String name;
    private String info;
    private Double ratingFull;
    private Boolean textMistake;
    private Boolean release;
}
