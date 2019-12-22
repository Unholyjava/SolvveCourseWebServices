package com.coursesolvve.webproject.dto;

import java.util.UUID;

public class ActorReadDTO {
    private UUID id;
    private String name;
    private String info;

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
}
