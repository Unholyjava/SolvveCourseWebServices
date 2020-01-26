package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.client.ClientCreateDTO;
import com.coursesolvve.webproject.dto.client.ClientPatchDTO;
import com.coursesolvve.webproject.dto.client.ClientPutDTO;
import com.coursesolvve.webproject.dto.client.ClientReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.ClientService;
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
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @Test
    public void testGetClient() throws Exception {
        ClientReadDTO read = createClientRead();

        Mockito.when(clientService.getClient(read.getId())).thenReturn(read);

        String resultJson = mvc.perform(get("/api/v1/clients/{id}", read.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        ClientReadDTO actualUser = objectMapper.readValue(resultJson, ClientReadDTO.class);
        Assertions.assertThat(actualUser).isEqualToComparingFieldByField(read);

        Mockito.verify(clientService).getClient(read.getId());
    }

    @Test
    public void testGetClientWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Client.class, wrongId);
        Mockito.when(clientService.getClient(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/clients/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testGetClientWrongUuidId() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/clients/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testCreateClient() throws Exception {
        ClientCreateDTO create = new ClientCreateDTO();
        create.setNickName("Client_test2_create");
        create.setLogin("ClientLogin_test2_create");
        create.setMail("test_mail");
        create.setName("test_Name");
        create.setPatronymic("test_Patronymic");
        create.setSurname("test_Surname");
        create.setTrust(true);
        create.setReviewRating(4);
        create.setActiveRating(5);
        create.setBlock(false);

        ClientReadDTO read = createClientRead();

        Mockito.when(clientService.createClient(create)).thenReturn(read);
        String resultJson = mvc.perform(post("/api/v1/clients")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ClientReadDTO actualClient = objectMapper.readValue(resultJson, ClientReadDTO.class);
        Assertions.assertThat(actualClient).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchClient() throws Exception {
        ClientPatchDTO patchDTO = new ClientPatchDTO();
        patchDTO.setNickName("Client_test2");
        patchDTO.setLogin("ClientLogin_test2");
        patchDTO.setMail("test_mail");
        patchDTO.setName("test_Name");
        patchDTO.setPatronymic("test_Patronymic");
        patchDTO.setSurname("test_Surname");
        patchDTO.setTrust(false);
        patchDTO.setReviewRating(2);
        patchDTO.setActiveRating(5);
        patchDTO.setIsBlock(false);

        ClientReadDTO read = createClientRead();

        Mockito.when(clientService.patchClient(read.getId(), patchDTO)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/clients/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patchDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ClientReadDTO actualClient = objectMapper.readValue(resultJson, ClientReadDTO.class);
        Assert.assertEquals(read, actualClient);
    }

    @Test
    public void testPutClient() throws Exception {
        ClientPutDTO putDTO = new ClientPutDTO();
        putDTO.setNickName("Client_test2");
        putDTO.setLogin("ClientLogin_test2");
        putDTO.setMail("test_mail");
        putDTO.setName("test_Name");
        putDTO.setPatronymic("test_Patronymic");
        putDTO.setSurname("test_Surname");
        putDTO.setTrust(false);
        putDTO.setReviewRating(2);
        putDTO.setActiveRating(5);
        putDTO.setIsBlock(false);

        ClientReadDTO read = createClientRead();

        Mockito.when(clientService.putClient(read.getId(), putDTO)).thenReturn(read);

        String resultJson = mvc.perform(put("/api/v1/clients/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(putDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ClientReadDTO actualClient = objectMapper.readValue(resultJson, ClientReadDTO.class);
        Assert.assertEquals(read, actualClient);
    }

    @Test
    public void testDeleteClient() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/clients/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(clientService).deleteClient(id);
    }

    private ClientReadDTO createClientRead() {
        ClientReadDTO read = new ClientReadDTO();
        read.setId(UUID.randomUUID());
        read.setNickName("Client_test1");
        read.setLogin("ClientLogin_test1");
        read.setMail("test_mail");
        read.setName("test_Name");
        read.setPatronymic("test_Patronymic");
        read.setSurname("test_Surname");
        read.setTrust(true);
        read.setReviewRating(4);
        read.setActiveRating(5);
        read.setBlock(false);
        return read;
    }
}
