package com.coursesolvve.webproject.dto.actor;

import lombok.Data;

@Data
public class ActorPutDTO {
    private String name;
    private String patronymic;
    private String surname;
    private String info;
    private Double ratingFull;
}
