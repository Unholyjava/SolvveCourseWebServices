package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String patronymic;
    private String surname;
    private String info;
    private double ratingFull;
}
