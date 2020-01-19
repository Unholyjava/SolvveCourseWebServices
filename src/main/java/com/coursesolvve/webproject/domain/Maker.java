package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Maker {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String patronymic;
    private String surname;

    @Enumerated(EnumType.STRING)
    private CreatorType creatorType;
}
