package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue
    private UUID id;

    private String info;
    private boolean newsMistake;
    private int likeRating;
    private Client client;
}
