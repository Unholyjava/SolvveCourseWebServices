package com.coursesolvve.webproject.dto;

import java.util.UUID;

public class FilmReadDTO {
    private UUID id;
    private String name;
    private String info;
    private int ratingFull;

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

    public int getRatingFull() {
        return ratingFull;
    }
    public void setRatingFull(int ratingFull) {
        this.ratingFull = ratingFull;
    }
}
