package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Client;
import com.coursesolvve.webproject.dto.client.ClientCreateDTO;
import com.coursesolvve.webproject.dto.client.ClientPatchDTO;
import com.coursesolvve.webproject.dto.client.ClientPutDTO;
import com.coursesolvve.webproject.dto.client.ClientReadDTO;
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

    public ClientReadDTO createClient(ClientCreateDTO create) {
        Client client = new Client();
        client.setNickName(create.getNickName());
        client.setLogin(create.getLogin());
        client.setMail(create.getMail());
        client.setName(create.getName());
        client.setPatronymic(create.getPatronymic());
        client.setSurname(create.getSurname());
        client.setTrust(create.getTrust());
        client.setReviewRating(create.getReviewRating());
        client.setActiveRating(create.getActiveRating());
        client.setIsBlock(create.getIsBlock());
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
        if (patch.getTrust() != null) {
            client.setTrust(patch.getTrust());
        }
        if (patch.getActiveRating() != null) {
            client.setActiveRating(patch.getActiveRating());
        }
        if (patch.getReviewRating() != null) {
            client.setReviewRating(patch.getReviewRating());
        }
        if (patch.getIsBlock() != null) {
            client.setIsBlock(patch.getIsBlock());
        }

        client = clientRepository.save(client);
        return toRead(client);
    }

    public ClientReadDTO updateClient(UUID id, ClientPutDTO put) {
        Client client = getClientRequired(id);

        client.setNickName(put.getNickName());
        client.setLogin(put.getLogin());
        client.setMail(put.getMail());
        client.setName(put.getName());
        client.setPatronymic(put.getPatronymic());
        client.setSurname(put.getSurname());
        client.setTrust(put.getTrust());
        client.setActiveRating(put.getActiveRating());
        client.setReviewRating(put.getReviewRating());
        client.setIsBlock(put.getIsBlock());

        client = clientRepository.save(client);
        return toRead(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.delete(getClientRequired(id));
    }

    private Client getClientRequired(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Client.class, id);
        });
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
        clientReadDTO.setTrust(client.getTrust());
        clientReadDTO.setReviewRating(client.getReviewRating());
        clientReadDTO.setActiveRating(client.getActiveRating());
        clientReadDTO.setIsBlock(client.getIsBlock());
        return clientReadDTO;
    }
}
