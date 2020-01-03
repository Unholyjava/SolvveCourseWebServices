package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.ClientCreateDTO;
import com.coursesolvve.webproject.dto.ClientReadDTO;
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
}
