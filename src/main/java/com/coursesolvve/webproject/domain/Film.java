package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String info;
    private double ratingFull;
    private boolean textMistake;
    private boolean release;
}
