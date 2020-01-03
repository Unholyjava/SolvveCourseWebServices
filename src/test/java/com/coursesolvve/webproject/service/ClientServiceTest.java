package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Access;
import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.ClientCreateDTO;
import com.coursesolvve.webproject.dto.ClientReadDTO;
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
        Client client = new Client();
        client.setNickName("Client_test1");
        client.setLogin("ClientLogin_test1");
        client.setTrust(true);
        client.setReviewRating(6);
        client.setActiveRating(7);
        client.setAccess(Access.REG_USER);
        client = clientRepository.save(client);

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
        create.setTrust(true);
        create.setReviewRating(6);
        create.setActiveRating(7);
        create.setAccess(Access.REG_USER);
        ClientReadDTO read = clientService.createClient(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Client client = clientRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(client);
    }
}
