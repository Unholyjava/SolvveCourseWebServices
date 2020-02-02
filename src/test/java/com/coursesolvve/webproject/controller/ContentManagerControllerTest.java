package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerCreateDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPatchDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPutDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.ContentManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContentManagerController.class)
public class ContentManagerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContentManagerService contentManagerService;

    @Test
    public void testGetContentManager() throws Exception {
        ContentManagerReadDTO read = createContentManagerRead();

        Mockito.when(contentManagerService.getContentManager(read.getId())).thenReturn(read);

        String resultJson = mvc.perform(get("/api/v1/content-managers/{id}", read.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        ContentManagerReadDTO actualContentManager = objectMapper.readValue(resultJson, ContentManagerReadDTO.class);
        Assertions.assertThat(actualContentManager).isEqualToComparingFieldByField(read);

        Mockito.verify(contentManagerService).getContentManager(read.getId());
    }

    @Test
    public void testGetActorWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(ContentManager.class, wrongId);
        Mockito.when(contentManagerService.getContentManager(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/content-managers/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testGetActorWrongUuidFormat() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/content-managers/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testCreateContentManager() throws Exception {
        ContentManagerCreateDTO create = new ContentManagerCreateDTO();
        create.setNickName("contManager_Nick");
        create.setLogin("contManager_Login");
        create.setMail("contManager_Mail");
        create.setName("contManager_Name");
        create.setPatronymic("contManager_Patron");
        create.setSurname("contManager_Surname");

        ContentManagerReadDTO read = createContentManagerRead();

        Mockito.when(contentManagerService.createContentManager(create)).thenReturn(read);
        String resultJson = mvc.perform(post("/api/v1/content-managers")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ContentManagerReadDTO actualContentManager = objectMapper.readValue(resultJson, ContentManagerReadDTO.class);
        Assertions.assertThat(actualContentManager).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchContentManager() throws Exception {
        ContentManagerPatchDTO patchDTO = new ContentManagerPatchDTO();
        patchDTO.setNickName("contManager_Nick");
        patchDTO.setLogin("contManager_Login");
        patchDTO.setMail("contManager_Mail");
        patchDTO.setName("contManager_Name");
        patchDTO.setPatronymic("contManager_Patron");
        patchDTO.setSurname("contManager_Surname");

        ContentManagerReadDTO read = createContentManagerRead();

        Mockito.when(contentManagerService.patchContentManager(read.getId(), patchDTO)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/content-managers/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patchDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ContentManagerReadDTO actualContentManager = objectMapper.readValue(resultJson, ContentManagerReadDTO.class);
        Assert.assertEquals(read, actualContentManager);
    }

    @Test
    public void testPutContentManager() throws Exception {
        ContentManagerPutDTO putDTO = new ContentManagerPutDTO();
        putDTO.setNickName("contManager_Nick");
        putDTO.setLogin("contManager_Login");
        putDTO.setMail("contManager_Mail");
        putDTO.setName("contManager_Name");
        putDTO.setPatronymic("contManager_Patron");
        putDTO.setSurname("contManager_Surname");

        ContentManagerReadDTO read = createContentManagerRead();

        Mockito.when(contentManagerService.putContentManager(read.getId(), putDTO)).thenReturn(read);

        String resultJson = mvc.perform(put("/api/v1/content-managers/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(putDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ContentManagerReadDTO actualContentManager = objectMapper.readValue(resultJson, ContentManagerReadDTO.class);
        Assert.assertEquals(read, actualContentManager);
    }

    @Test
    public void testDeleteActor() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/content-managers/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(contentManagerService).deleteContentManager(id);
    }

    private ContentManagerReadDTO createContentManagerRead() {
        ContentManagerReadDTO read = new ContentManagerReadDTO();
        read.setId(UUID.randomUUID());
        read.setNickName("contManager_Nick");
        read.setLogin("contManager_Login");
        read.setMail("contManager_Mail");
        read.setName("contManager_Name");
        read.setPatronymic("contManager_Patron");
        read.setSurname("contManager_Surname");
        return read;
    }
}
