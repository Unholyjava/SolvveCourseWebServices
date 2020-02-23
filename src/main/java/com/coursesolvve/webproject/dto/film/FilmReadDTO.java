package com.coursesolvve.webproject.dto.film;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class FilmReadDTO {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String info;
    private Double ratingFull;
    private Boolean textMistake;
    private Boolean release;
}
