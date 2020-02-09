package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue
    private UUID id;

    private String info;
    private Boolean newsMistake;
    private Integer likeRating;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private ContentManager contentManager;
}
