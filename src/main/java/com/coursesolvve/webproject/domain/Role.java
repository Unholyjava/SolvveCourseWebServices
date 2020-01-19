package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String info;
    private double ratingFull;
    private Film film;
    private Actor actor;
}
