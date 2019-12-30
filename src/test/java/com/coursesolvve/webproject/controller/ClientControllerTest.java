package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Access;
import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.ClientReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @Test
    public void testGetClient() throws Exception {
        ClientReadDTO client = new ClientReadDTO();
        client.setId(UUID.randomUUID());
        client.setNickName("Client_test1");
        client.setLogin("ClientLogin_test1");
        client.setTrust(true);
        client.setReviewRating(4);
        client.setActiveRating(5);
        client.setAccess(Access.REG_USER);

        Mockito.when(clientService.getClient(client.getId())).thenReturn(client);

        String resultJson = mvc.perform(get("/api/v1/clients/{id}", client.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        ClientReadDTO actualUser = objectMapper.readValue(resultJson, ClientReadDTO.class);
        Assertions.assertThat(actualUser).isEqualToComparingFieldByField(client);

        Mockito.verify(clientService).getClient(client.getId());
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
}
