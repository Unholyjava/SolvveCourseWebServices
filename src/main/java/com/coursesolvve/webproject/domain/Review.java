package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue
    private UUID id;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    private String filmReview;
    private Integer filmRating;
    private String roleReview;
    private Boolean reviewMistake;
    private Integer likeRating;
    private Instant dataReview;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Client client;
}
