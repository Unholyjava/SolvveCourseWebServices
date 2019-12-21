package com.coursesolvve.webproject.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
public class Rating {

    @Id
    @GeneratedValue
    private UUID id;

    private int rating;
    private String feedback;
    Film film;
    User user;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
