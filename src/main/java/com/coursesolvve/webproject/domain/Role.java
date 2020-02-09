package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Actor actor;

    private String name;
    private String info;
    private Double ratingFull;
}
