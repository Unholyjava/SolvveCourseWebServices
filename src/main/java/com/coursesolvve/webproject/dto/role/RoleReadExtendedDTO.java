package com.coursesolvve.webproject.dto.role;

import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleReadExtendedDTO {
    private UUID id;
    private String name;
    private String info;
    private double ratingFull;

    private ActorReadDTO actor;
}

