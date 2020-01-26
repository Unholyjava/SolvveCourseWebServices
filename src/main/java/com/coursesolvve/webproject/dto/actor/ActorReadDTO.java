package com.coursesolvve.webproject.dto.actor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ActorReadDTO {
    private UUID id;
    private String name;
    private String patronymic;
    private String surname;
    private String info;
    private double ratingFull;
}

