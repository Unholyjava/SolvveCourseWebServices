package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

//@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    private UUID id;

    private ReviewStatus reviewStatus;
    private String filmReview;
    private Integer filmRating;
    private String roleReview;
    private Boolean reviewMistake;
    private Integer likeRating;
    private Instant dataReview;
    private Film film;
    private Client client;
    private Role role;
}
