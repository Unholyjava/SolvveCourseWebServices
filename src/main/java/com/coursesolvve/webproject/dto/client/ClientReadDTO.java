package com.coursesolvve.webproject.dto.client;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ClientReadDTO {
    private UUID id;
    private String nickName;
    private String login;
    private String mail;
    private String name;
    private String patronymic;
    private String surname;
    private Boolean trust;
    private Integer reviewRating;
    private Integer activeRating;
    private Boolean isBlock;
}
