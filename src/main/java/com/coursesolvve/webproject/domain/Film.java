package com.coursesolvve.webproject.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Film {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String info;
    private int ratingFull;
    private boolean textMistake;
    private boolean release;

    @Enumerated(EnumType.STRING)
    private Genres genres;

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

    public boolean isTextMistake() {
        return textMistake;
    }
    public void setTextMistake(boolean textMistake) {
        this.textMistake = textMistake;
    }

    public boolean isRelease() {
        return release;
    }
    public void setRelease(boolean release) {
        this.release = release;
    }

    public Genres getGenres() {
        return genres;
    }
    public void setGenres(Genres genres) {
        this.genres = genres;
    }
}
