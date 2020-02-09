package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerCreateDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPatchDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPutDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ContentManagerRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = "delete from content_manager", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ContentManagerServiceTest {

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    @Autowired
    private ContentManagerService contentManagerService;

    @Test
    public void testGetContentManager() {
        ContentManager contentManager = createContentManager();

        ContentManagerReadDTO readDTO = contentManagerService.getContentManager(contentManager.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(contentManager);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetContentManagerWrongId() {
        contentManagerService.getContentManager(UUID.randomUUID());
    }

    @Test
    public void testCreateContentManager() {
        ContentManagerCreateDTO create = new ContentManagerCreateDTO();
        create.setNickName("contManager_Nick");
        create.setLogin("contManager_Login");
        create.setMail("contManager_Mail");
        create.setName("contManager_Name");
        create.setPatronymic("contManager_Patron");
        create.setSurname("contManager_Surname");
        ContentManagerReadDTO read = contentManagerService.createContentManager(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        ContentManager contentManager = contentManagerRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(contentManager);
    }

    @Test
    public void testPatchContentManager() {
        ContentManager contentManager = createContentManager();

        ContentManagerPatchDTO patch = new ContentManagerPatchDTO();
        patch.setNickName("contManager_Nik");
        patch.setLogin("contManager_Login");
        patch.setMail("contManager_Mail");
        patch.setName("contManager_Name");
        patch.setPatronymic("contManager_Patron");
        patch.setSurname("contManager_Surname");
        ContentManagerReadDTO read = contentManagerService.patchContentManager(contentManager.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        contentManager = contentManagerRepository.findById(read.getId()).get();
        Assertions.assertThat(contentManager).isEqualToIgnoringGivenFields(read, "news");
    }

    @Test
    public void testPatchActorEmptyPatch() {
        ContentManager contentManager = createContentManager();
        ContentManagerPatchDTO patch = new ContentManagerPatchDTO();
        ContentManagerReadDTO read = contentManagerService.patchContentManager(contentManager.getId(), patch);
        Assertions.assertThat(read).hasNoNullFieldsOrProperties();

        ContentManager actorAfterUpdate = contentManagerRepository.findById(read.getId()).get();
        Assertions.assertThat(actorAfterUpdate).hasNoNullFieldsOrProperties();

        Assertions.assertThat(contentManager).isEqualToIgnoringGivenFields(actorAfterUpdate, "news");
    }

    @Test
    public void testUpdateContentManager() {
        ContentManager contentManager = createContentManager();

        ContentManagerPutDTO put = new ContentManagerPutDTO();
        put.setNickName("contManager_Nik");
        put.setLogin("contManager_Login");
        put.setMail("contManager_Mail");
        put.setName("contManager_Name");
        put.setPatronymic("contManager_Patron");
        put.setSurname("contManager_Surname");

        ContentManagerReadDTO read = contentManagerService.updateContentManager(contentManager.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        contentManager = contentManagerRepository.findById(read.getId()).get();
        Assertions.assertThat(contentManager).isEqualToIgnoringGivenFields(read, "news");
    }

    @Test
    public void testDeleteContentManager() {
        ContentManager contentManager = createContentManager();

        contentManagerService.deleteContentManager(contentManager.getId());
        Assert.assertFalse(contentManagerRepository.existsById(contentManager.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteContentManagerNotFound() {
        contentManagerService.deleteContentManager(UUID.randomUUID());
    }

    private ContentManager createContentManager() {
        ContentManager contentManager = new ContentManager();
        contentManager.setNickName("contManager_Nick");
        contentManager.setLogin("contManager_Login");
        contentManager.setMail("contManager_Mail");
        contentManager.setName("contManager_Name");
        contentManager.setPatronymic("contManager_Patron");
        contentManager.setSurname("contManager_Surname");
        contentManager = contentManagerRepository.save(contentManager);
        return contentManager;
    }
}
