package com.sample.controller;

import com.sample.dao.ClientRepository;
import com.sample.dto.ClientRq;
import com.sample.dto.ClientRs;
import com.sample.entity.Client;
import com.sample.entity.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URL;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {
    @LocalServerPort
    private int port;

    protected URL base;

    @Autowired
    protected TestRestTemplate template;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void init() throws Exception {
        base = new URL("http://localhost:" + port);
    }

    @Test
    public void testClients() {
        String name = "Test" + new Random().nextInt();
        Client client = buildClient(name);

        clientRepository.save(client);

        String url = base.toString() + "/clients";

        ClientRq rq = new ClientRq(name);

        ClientRs rs = template.postForEntity(url, rq, ClientRs.class).getBody();

        assertNotNull(rs);
        assertEquals(rs.getItems().size(), 1);

        for (Client item : rs.getItems()) {
            assertNotNull(item.getBirthday());
        }
    }

    public static Client buildClient(String name) {
        Client client = new Client();

        client.setName(name);
        client.setBirthday(LocalDate.now());
        client.setCreatedAt(OffsetDateTime.now());
        client.setXml("<XML/>");
        client.setStatus(Status.ACTIVE);
        client.setVip(true);
        return client;
    }
}