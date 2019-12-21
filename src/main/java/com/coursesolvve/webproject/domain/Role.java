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

    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
