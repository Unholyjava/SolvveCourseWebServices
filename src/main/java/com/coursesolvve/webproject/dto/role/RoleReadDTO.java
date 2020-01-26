package com.coursesolvve.webproject.dto.role;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleReadDTO {
    private UUID id;

    private UUID actorId;

    private String name;
    private String info;
    private double ratingFull;
}

