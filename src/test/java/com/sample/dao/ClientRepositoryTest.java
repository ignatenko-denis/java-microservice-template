package com.sample.dao;

import com.sample.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import java.util.Random;

import static com.sample.controller.ClientControllerTest.buildClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(locations = "/config/application.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void findByNameOrderByBirthdayAsc() {
        String name = "Test" + new Random().nextInt();
        Client client = buildClient(name);

        clientRepository.save(client);

        Page<Client> actual = clientRepository.findByNameOrderByBirthdayAsc(name, PageRequest.of(0, 10));
        assertEquals(1, actual.getNumberOfElements());

        clientRepository.delete(client);
    }
}