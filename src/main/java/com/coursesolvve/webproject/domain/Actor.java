package com.coursesolvve.webproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String info;
    private int rating;

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

    public String getInfo() { return info; }
    public void setInfo(String info) {
        this.info = info;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) { this.rating = rating; }
}
