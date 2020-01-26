package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.domain.Role;
import com.coursesolvve.webproject.dto.actor.ActorCreateDTO;
import com.coursesolvve.webproject.dto.actor.ActorPatchDTO;
import com.coursesolvve.webproject.dto.actor.ActorPutDTO;
import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import com.coursesolvve.webproject.dto.role.RoleReadExtendedDTO;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    public RoleReadExtendedDTO toReadExtended(Role role) {
        RoleReadExtendedDTO roleReadDTO = new RoleReadExtendedDTO();
        roleReadDTO.setId(role.getId());
        roleReadDTO.setName(role.getName());
        roleReadDTO.setInfo(role.getInfo());
        roleReadDTO.setRatingFull(role.getRatingFull());
        roleReadDTO.setActor(toRead(role.getActor()));
        return roleReadDTO;
    }

    public ActorReadDTO toRead(Actor actor) {
        ActorReadDTO actorReadDTO = new ActorReadDTO();
        actorReadDTO.setId(actor.getId());
        actorReadDTO.setName(actor.getName());
        actorReadDTO.setPatronymic(actor.getPatronymic());
        actorReadDTO.setSurname(actor.getSurname());
        actorReadDTO.setInfo(actor.getInfo());
        actorReadDTO.setRatingFull(actor.getRatingFull());
        return actorReadDTO;
    }

    public Actor toEntity(ActorCreateDTO create) {
        Actor actor = new Actor();
        actor.setName(create.getName());
        actor.setPatronymic(create.getPatronymic());
        actor.setSurname(create.getSurname());
        actor.setInfo(create.getInfo());
        actor.setRatingFull(create.getRatingFull());
        return actor;
    }

    public void patchEntity(ActorPatchDTO patch, Actor actor) {
        if (patch.getName() != null) {
            actor.setName(patch.getName());
        }
        if (patch.getPatronymic() != null) {
            actor.setPatronymic(patch.getPatronymic());
        }
        if (patch.getSurname() != null) {
            actor.setSurname(patch.getSurname());
        }
        if (patch.getInfo() != null) {
            actor.setInfo(patch.getInfo());
        }
        if (patch.getRatingFull() != null) {
            actor.setRatingFull(patch.getRatingFull());
        }
    }

    public void putEntity(ActorPutDTO put, Actor actor) {
        actor.setName(put.getName());
        actor.setPatronymic(put.getPatronymic());
        actor.setSurname(put.getSurname());
        actor.setInfo(put.getInfo());
        actor.setRatingFull(put.getRatingFull());
    }
}
