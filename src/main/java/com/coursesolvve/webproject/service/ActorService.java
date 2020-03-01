package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.actor.ActorCreateDTO;
import com.coursesolvve.webproject.dto.actor.ActorPatchDTO;
import com.coursesolvve.webproject.dto.actor.ActorPutDTO;
import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import com.coursesolvve.webproject.repository.ActorRepository;
import com.coursesolvve.webproject.repository.RepositoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private TranslationService translationService;

    @Autowired
    private RepositoryHelper repositoryHelper;

    public ActorReadDTO getActor(UUID id) {
        Actor actor = repositoryHelper.getEntityRequired(Actor.class, id);
        return translationService.toRead(actor);
    }

    public ActorReadDTO createActor(ActorCreateDTO create) {
        Actor actor = translationService.toEntity(create);

        actor = actorRepository.save(actor);
        return translationService.toRead(actor);
    }

    public ActorReadDTO patchActor(UUID id, ActorPatchDTO patch) {
        Actor actor = repositoryHelper.getEntityRequired(Actor.class, id);

        translationService.patchEntity(patch, actor);

        actor = actorRepository.save(actor);
        return translationService.toRead(actor);
    }

    public ActorReadDTO updateActor(UUID id, ActorPutDTO put) {
        Actor actor = repositoryHelper.getEntityRequired(Actor.class, id);

        translationService.updateEntity(put, actor);

        actor = actorRepository.save(actor);
        return translationService.toRead(actor);
    }

    public void deleteActor(UUID id) {
        actorRepository.delete(repositoryHelper.getEntityRequired(Actor.class, id));
    }
}
