package com.coursesolvve.webproject.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
public class Maker {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private Creator creator;
    private Film film;

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

    public Creator getCreator() {
        return creator;
    }
    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
}
