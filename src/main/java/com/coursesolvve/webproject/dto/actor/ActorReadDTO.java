package com.coursesolvve.webproject.dto.actor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ActorReadDTO {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String patronymic;
    private String surname;
    private String info;
    private Double ratingFull;
}

