package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerCreateDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPatchDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPutDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ContentManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentManagerService {

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Autowired
    private TranslationService translationService;

    public ContentManagerReadDTO getContentManager(UUID id) {
        ContentManager contentManager = getContentManagerRequired(id);
        return translationService.toRead(contentManager);
    }

    public ContentManagerReadDTO createContentManager(ContentManagerCreateDTO create) {
        ContentManager contentManager = translationService.toEntity(create);

        contentManager = contentManagerRepository.save(contentManager);
        return translationService.toRead(contentManager);
    }

    public ContentManagerReadDTO patchContentManager(UUID id, ContentManagerPatchDTO patch) {
        ContentManager contentManager = getContentManagerRequired(id);

        translationService.patchEntity(patch, contentManager);

        contentManager = contentManagerRepository.save(contentManager);
        return translationService.toRead(contentManager);
    }

    public ContentManagerReadDTO putContentManager(UUID id, ContentManagerPutDTO put) {
        ContentManager contentManager = getContentManagerRequired(id);

        translationService.putEntity(put, contentManager);

        contentManager = contentManagerRepository.save(contentManager);
        return translationService.toRead(contentManager);
    }

    public void deleteContentManager(UUID id) {
        contentManagerRepository.delete(getContentManagerRequired(id));
    }

    private ContentManager getContentManagerRequired(UUID id) {
        return contentManagerRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(ContentManager.class, id);
        });
    }
}
