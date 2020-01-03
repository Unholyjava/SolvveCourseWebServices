package com.coursesolvve.webproject.dto;

import java.util.Objects;

public class ActorCreateDTO {
    private String name;
    private String info;
    private int rating;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorCreateDTO)) return false;
        ActorCreateDTO that = (ActorCreateDTO) o;
        return getRating() == that.getRating() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getInfo(), that.getInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getInfo(), getRating());
    }
}
