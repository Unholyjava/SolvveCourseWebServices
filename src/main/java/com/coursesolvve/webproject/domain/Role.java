package com.coursesolvve.webproject.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String info;
    private int rating;
    private Film film;
    private Actor actor;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }
    public void setActor(Actor actor) { this.actor = actor; }
}
