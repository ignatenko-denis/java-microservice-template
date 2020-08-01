package com.sample.controller;

import com.sample.dao.ClientRepository;
import com.sample.dto.ClientRq;
import com.sample.dto.ClientRs;
import com.sample.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/clients")
    public ClientRs clients(@RequestBody ClientRq rq) {
        ClientRs rs = new ClientRs();

        Page<Client> clients = clientRepository.findByName(rq.getName(), PageRequest.of(0, 10));

        for (Client client : clients) {
            rs.getItems().add(client);
        }

        return rs;
    }
}
