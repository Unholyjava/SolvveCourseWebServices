package com.coursesolvve.webproject.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Film {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;
    private String[] roles;
    private String[] actors;
    private String[] filmmakers;

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

    public String[] getActors() {
        return actors;
    }
    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getRoles() {
        return roles;
    }
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getFilmmakers() {
        return filmmakers;
    }
    public void setFilmmakers(String[] filmmakers) {
        this.filmmakers = filmmakers;
    }
}
