package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.ClientCreateDTO;
import com.coursesolvve.webproject.dto.ClientPatchDTO;
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
        Client client = getClientRequired(id);
        return toRead(client);
    }

    private ClientReadDTO toRead(Client client) {
        ClientReadDTO clientReadDTO = new ClientReadDTO();
        clientReadDTO.setId(client.getId());
        clientReadDTO.setNickName(client.getNickName());
        clientReadDTO.setLogin(client.getLogin());
        clientReadDTO.setMail(client.getMail());
        clientReadDTO.setName(client.getName());
        clientReadDTO.setPatronymic(client.getPatronymic());
        clientReadDTO.setSurname(client.getSurname());
        clientReadDTO.setTrust(client.isTrust());
        clientReadDTO.setReviewRating(client.getReviewRating());
        clientReadDTO.setActiveRating(client.getActiveRating());
        clientReadDTO.setBlock(client.isBlock());
        return clientReadDTO;
    }

    public ClientReadDTO createClient(ClientCreateDTO create) {
        Client client = new Client();
        client.setNickName(create.getNickName());
        client.setLogin(create.getLogin());
        client.setMail(create.getMail());
        client.setName(create.getName());
        client.setPatronymic(create.getPatronymic());
        client.setSurname(create.getSurname());
        client.setTrust(create.isTrust());
        client.setReviewRating(create.getReviewRating());
        client.setActiveRating(create.getActiveRating());
        client.setBlock(create.isBlock());
        client = clientRepository.save(client);
        return toRead(client);
    }

    public ClientReadDTO patchClient(UUID id, ClientPatchDTO patch) {
        Client client = getClientRequired(id);

        if (patch.getNickName() != null) {
            client.setNickName(patch.getNickName());
        }
        if (patch.getLogin() != null) {
            client.setLogin(patch.getLogin());
        }
        if (patch.getMail() != null) {
            client.setMail(patch.getMail());
        }
        if (patch.getName() != null) {
            client.setName(patch.getName());
        }
        if (patch.getPatronymic() != null) {
            client.setPatronymic(patch.getPatronymic());
        }
        if (patch.getSurname() != null) {
            client.setSurname(patch.getSurname());
        }
        client.setTrust(patch.isTrust());
        client.setActiveRating(patch.getActiveRating());
        client.setReviewRating(patch.getReviewRating());
        client.setBlock(patch.isBlock());

        client = clientRepository.save(client);
        return toRead(client);
    }

    private Client getClientRequired(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Client.class, id);
        });
    }

    public void deleteClient(UUID id) {
        clientRepository.delete(getClientRequired(id));
    }
}
