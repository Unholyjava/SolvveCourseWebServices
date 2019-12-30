package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.ClientReadDTO;
import com.coursesolvve.webproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
