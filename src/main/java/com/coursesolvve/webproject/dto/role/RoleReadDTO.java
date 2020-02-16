package com.coursesolvve.webproject.dto.role;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RoleReadDTO {
    private UUID id;

    private UUID actorId;

    private Instant createdAt;
    private Instant updatedAt;

    private String name;
    private String info;
    private double ratingFull;
}

