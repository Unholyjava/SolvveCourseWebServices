package com.coursesolvve.webproject.dto;

import lombok.Data;

@Data
public class ActorPatchDTO {
    private String name;
    private String patronymic;
    private String surname;
    private String info;
    private double ratingFull;
}
