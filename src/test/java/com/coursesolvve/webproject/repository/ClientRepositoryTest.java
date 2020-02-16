package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from client", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testSave() {
        Client c = new Client();
        c = clientRepository.save(c);
        assertNotNull(c.getId());
        assertTrue(clientRepository.findById(c.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        Client client = createClient();

        Instant createdAtBeforeReload = client.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        client = clientRepository.findById(client.getId()).get();

        Instant createdAtAfterReload = client.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        Client client = createClient();

        Instant createdAt = client.getCreatedAt();
        Instant updatedAtBeforeReload = client.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        client.setIsBlock(Boolean.TRUE);
        client = clientRepository.save(client);
        client = clientRepository.findById(client.getId()).get();
        Instant updatedAtAfterReload = client.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
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
        client.setIsBlock(false);
        client = clientRepository.save(client);
        return client;
    }
}
