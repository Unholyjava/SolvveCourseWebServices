package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
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
        Actor actor = actorRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Actor.class, id);
        });
        return toRead(actor);
    }

    private ActorReadDTO toRead(Actor actor) {
        ActorReadDTO actorReadDTO = new ActorReadDTO();
        actorReadDTO.setId(actor.getId());
        actorReadDTO.setName(actor.getName());
        actorReadDTO.setInfo(actor.getInfo());
        actorReadDTO.setRating(actor.getRating());
        return actorReadDTO;
    }
}
