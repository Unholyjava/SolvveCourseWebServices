package com.coursesolvve.webproject.dto.actor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ActorCreateDTO {
    private String name;
    private String patronymic;
    private String surname;
    private String info;
    private Double ratingFull;
}
