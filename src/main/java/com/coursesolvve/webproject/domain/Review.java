package com.coursesolvve.webproject.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
public class Review {

    @Id
    @GeneratedValue
    private UUID id;

    private Status status;
    private String filmReview;
    private int filmRating;
    private String roleReview;
    private int roleRating;
    private String spoiler;
    private int reviewRating;
    private boolean reviewMistake;
    private Film film;
    private Client client;
    private Role role;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFilmReview() {
        return filmReview;
    }
    public void setFilmReview(String filmReview) {
        this.filmReview = filmReview;
    }

    public int getFilmRating() {
        return filmRating;
    }
    public void setFilmRating(int filmRating) {
        this.filmRating = filmRating;
    }

    public String getRoleReview() {
        return roleReview;
    }
    public void setRoleReview(String roleReview) {
        this.roleReview = roleReview;
    }

    public int getRoleRating() {
        return roleRating;
    }
    public void setRoleRating(int roleRating) {
        this.roleRating = roleRating;
    }

    public String getSpoiler() {
        return spoiler;
    }
    public void setSpoiler(String spoiler) {
        this.spoiler = spoiler;
    }

    public int getReviewRating() {
        return reviewRating;
    }
    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public boolean isReviewMistake() {
        return reviewMistake;
    }
    public void setReviewMistake(boolean reviewMistake) {
        this.reviewMistake = reviewMistake;
    }

    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
