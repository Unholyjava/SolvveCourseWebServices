package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.client.ClientCreateDTO;
import com.coursesolvve.webproject.dto.client.ClientPatchDTO;
import com.coursesolvve.webproject.dto.client.ClientPutDTO;
import com.coursesolvve.webproject.dto.client.ClientReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ClientRepository;
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
@Sql(statements = "delete from client", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientServiceTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Test
    public void testGetClient() {
        Client client = createClient();

        ClientReadDTO readDTO = clientService.getClient(client.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(client);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetClientWrongId() {
        clientService.getClient(UUID.randomUUID());
    }

    @Test
    public void testCreateClient() {
        ClientCreateDTO create = new ClientCreateDTO();
        create.setNickName("Client_test2_create");
        create.setLogin("ClientLogin_test2_cr");
        create.setMail("test_mail");
        create.setName("test_Name");
        create.setPatronymic("test_Patronymic");
        create.setSurname("test_Surname");
        create.setTrust(true);
        create.setReviewRating(6);
        create.setActiveRating(7);
        create.setBlock(false);
        ClientReadDTO read = clientService.createClient(create);

        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Client client = clientRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(client);
    }

    @Test
    public void testPatchClient() {
        Client client = createClient();

        ClientPatchDTO patch = new ClientPatchDTO();
        patch.setNickName("Client_test1");
        patch.setLogin("ClientLogin_test2");
        patch.setMail("test_mail2");
        patch.setName("test_Name2");
        patch.setPatronymic("test_Patronymic2");
        patch.setSurname("test_Surname2");
        patch.setTrust(false);
        patch.setReviewRating(1);
        patch.setActiveRating(7);
        patch.setIsBlock(false);
        ClientReadDTO read = clientService.patchClient(client.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        client = clientRepository.findById(read.getId()).get();
        Assertions.assertThat(client).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchClientEmptyPatch() {
        Client client = createClient();
        ClientPatchDTO patch = new ClientPatchDTO();
        ClientReadDTO read = clientService.patchClient(client.getId(), patch);
        Assertions.assertThat(read).hasNoNullFieldsOrProperties();

        Client clientAfterUpdate = clientRepository.findById(read.getId()).get();
        Assertions.assertThat(clientAfterUpdate).hasNoNullFieldsOrProperties();

        Assertions.assertThat(client).isEqualToComparingFieldByField(clientAfterUpdate);
    }

    @Test
    public void testPutClient() {
        Client client = createClient();

        ClientPutDTO put = new ClientPutDTO();
        put.setNickName("Client_test1");
        put.setLogin("ClientLogin_test2");
        put.setMail("test_mail2");
        put.setName("test_Name2");
        put.setPatronymic("test_Patronymic2");
        put.setSurname("test_Surname2");
        put.setTrust(false);
        put.setReviewRating(1);
        put.setActiveRating(7);
        put.setIsBlock(false);
        ClientReadDTO read = clientService.putClient(client.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        client = clientRepository.findById(read.getId()).get();
        Assertions.assertThat(client).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testDeleteClient() {
        Client client = createClient();

        clientService.deleteClient(client.getId());
        Assert.assertFalse(clientRepository.existsById(client.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteClientNotFound() {
        clientService.deleteClient(UUID.randomUUID());
    }

    private Client createClient() {
        Client client = new Client();
        client.setNickName("Client_test1");
        client.setLogin("ClientLogin_test1");
        client.setMail("test_mail");
        client.setName("test_Name");
        client.setPatronymic("test_Patronymic");
        client.setSurname("test_Surname");
        client.setTrust(true);
        client.setReviewRating(6);
        client.setActiveRating(7);
        client.setBlock(false);
        client = clientRepository.save(client);
        return client;
    }
}
