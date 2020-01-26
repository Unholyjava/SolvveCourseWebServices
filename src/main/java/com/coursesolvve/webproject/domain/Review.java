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
    private int filmRating;
    private String roleReview;
    private boolean reviewMistake;
    private int likeRating;
    private Instant dataReview;
    private Film film;
    private Client client;
    private Role role;
}
