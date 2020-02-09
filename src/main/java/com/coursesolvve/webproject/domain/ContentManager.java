package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ContentManager extends Customer{

    @Id
    @GeneratedValue
    private UUID id;

    private String nickName;
    private String login;
    private String mail;
    private String name;
    private String patronymic;
    private String surname;

    @OneToMany(mappedBy = "contentManager")
    private List<News> news;
}
