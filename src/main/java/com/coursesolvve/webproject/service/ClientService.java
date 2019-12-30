package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.ClientReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientReadDTO getClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Client.class, id);
        });
        return toRead(client);
    }

    private ClientReadDTO toRead(Client client) {
        ClientReadDTO clientReadDTO = new ClientReadDTO();
        clientReadDTO.setId(client.getId());
        clientReadDTO.setNickName(client.getNickName());
        clientReadDTO.setLogin(client.getLogin());
        clientReadDTO.setTrust(client.isTrust());
        clientReadDTO.setReviewRating(client.getReviewRating());
        clientReadDTO.setActiveRating(client.getActiveRating());
        clientReadDTO.setAccess(client.getAccess());
        return clientReadDTO;
    }
}
