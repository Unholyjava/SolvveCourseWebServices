package com.coursesolvve.webproject.dto.role;

import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RoleReadExtendedDTO {
    private UUID id;

    private ActorReadDTO actor;

    private Instant createdAt;
    private Instant updatedAt;

    private String name;
    private String info;
    private Double ratingFull;
}

