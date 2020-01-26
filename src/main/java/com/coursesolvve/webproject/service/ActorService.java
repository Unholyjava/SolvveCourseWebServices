package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.actor.ActorCreateDTO;
import com.coursesolvve.webproject.dto.actor.ActorPatchDTO;
import com.coursesolvve.webproject.dto.actor.ActorPutDTO;
import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private TranslationService translationService;

    public ActorReadDTO getActor(UUID id) {
        Actor actor = getActorRequired(id);
        return translationService.toRead(actor);
    }

    public ActorReadDTO createActor(ActorCreateDTO create) {
        Actor actor = translationService.toEntity(create);

        actor = actorRepository.save(actor);
        return translationService.toRead(actor);
    }

    public ActorReadDTO patchActor(UUID id, ActorPatchDTO patch) {
        Actor actor = getActorRequired(id);

        translationService.patchEntity(patch, actor);

        actor = actorRepository.save(actor);
        return translationService.toRead(actor);
    }

    public ActorReadDTO putActor(UUID id, ActorPutDTO put) {
        Actor actor = getActorRequired(id);

        translationService.putEntity(put, actor);

        actor = actorRepository.save(actor);
        return translationService.toRead(actor);
    }

    public void deleteActor(UUID id) {
        actorRepository.delete(getActorRequired(id));
    }

    private Actor getActorRequired(UUID id) {
        return actorRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Actor.class, id);
        });
    }
}
