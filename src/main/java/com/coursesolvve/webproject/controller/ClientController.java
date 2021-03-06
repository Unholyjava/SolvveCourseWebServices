package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.client.ClientCreateDTO;
import com.coursesolvve.webproject.dto.client.ClientPatchDTO;
import com.coursesolvve.webproject.dto.client.ClientPutDTO;
import com.coursesolvve.webproject.dto.client.ClientReadDTO;
import com.coursesolvve.webproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ClientReadDTO getClient(@PathVariable UUID id) {
        return clientService.getClient(id);
    }

    @PostMapping
    public ClientReadDTO createClient(@RequestBody ClientCreateDTO createDTO) {
        return clientService.createClient(createDTO);
    }

    @PatchMapping("/{id}")
    public ClientReadDTO patchClient(@PathVariable UUID id, @RequestBody ClientPatchDTO patch) {
        return clientService.patchClient(id, patch);
    }

    @PutMapping("/{id}")
    public ClientReadDTO updateClient(@PathVariable UUID id, @RequestBody ClientPutDTO put) {
        return clientService.updateClient(id, put);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }
}
