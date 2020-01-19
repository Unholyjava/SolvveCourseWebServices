package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.ActorCreateDTO;
import com.coursesolvve.webproject.dto.ActorPatchDTO;
import com.coursesolvve.webproject.dto.ActorReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public ActorReadDTO getActor(UUID id) {
        Actor actor = getActorRequired(id);
        return toRead(actor);
    }

    private Actor getActorRequired(UUID id) {
        return actorRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Actor.class, id);
        });
    }

    private ActorReadDTO toRead(Actor actor) {
        ActorReadDTO actorReadDTO = new ActorReadDTO();
        actorReadDTO.setId(actor.getId());
        actorReadDTO.setName(actor.getName());
        actorReadDTO.setPatronymic(actor.getPatronymic());
        actorReadDTO.setSurname(actor.getSurname());
        actorReadDTO.setInfo(actor.getInfo());
        actorReadDTO.setRatingFull(actor.getRatingFull());
        return actorReadDTO;
    }

    public ActorReadDTO createActor(ActorCreateDTO create) {
        Actor actor = new Actor();
        actor.setName(create.getName());
        actor.setPatronymic(create.getPatronymic());
        actor.setSurname(create.getSurname());
        actor.setInfo(create.getInfo());
        actor.setRatingFull(create.getRatingFull());
        actor = actorRepository.save(actor);
        return toRead(actor);
    }

    public ActorReadDTO patchActor(UUID id, ActorPatchDTO patch) {
        Actor actor = getActorRequired(id);

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

        actor.setRatingFull(patch.getRatingFull());

        actor = actorRepository.save(actor);
        return toRead(actor);
    }

    public void deleteActor(UUID id) {
        actorRepository.delete(getActorRequired(id));
    }
}
