package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerCreateDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPatchDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPutDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import com.coursesolvve.webproject.repository.ContentManagerRepository;
import com.coursesolvve.webproject.repository.RepositoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentManagerService {

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Autowired
    private TranslationService translationService;

    @Autowired
    private RepositoryHelper repositoryHelper;

    public ContentManagerReadDTO getContentManager(UUID id) {
        ContentManager contentManager =
                repositoryHelper.getEntityRequired(ContentManager.class, id);
        return translationService.toRead(contentManager);
    }

    public ContentManagerReadDTO createContentManager(ContentManagerCreateDTO create) {
        ContentManager contentManager = translationService.toEntity(create);

        contentManager = contentManagerRepository.save(contentManager);
        return translationService.toRead(contentManager);
    }

    public ContentManagerReadDTO patchContentManager(UUID id, ContentManagerPatchDTO patch) {
        ContentManager contentManager =
                repositoryHelper.getEntityRequired(ContentManager.class, id);

        translationService.patchEntity(patch, contentManager);

        contentManager = contentManagerRepository.save(contentManager);
        return translationService.toRead(contentManager);
    }

    public ContentManagerReadDTO updateContentManager(UUID id, ContentManagerPutDTO put) {
        ContentManager contentManager =
                repositoryHelper.getEntityRequired(ContentManager.class, id);

        translationService.updateEntity(put, contentManager);

        contentManager = contentManagerRepository.save(contentManager);
        return translationService.toRead(contentManager);
    }

    public void deleteContentManager(UUID id) {
        contentManagerRepository.delete(
                repositoryHelper.getEntityRequired(ContentManager.class, id));
    }
}
