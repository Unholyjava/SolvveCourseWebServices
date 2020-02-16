package com.coursesolvve.webproject.dto.contentmanager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ContentManagerReadDTO {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private String nickName;
    private String login;
    private String mail;
    private String name;
    private String patronymic;
    private String surname;
}
