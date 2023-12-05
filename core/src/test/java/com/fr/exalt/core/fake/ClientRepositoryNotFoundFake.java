package com.fr.exalt.core.fake;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.port.secondary.ClientRepository;

public class ClientRepositoryNotFoundFake implements ClientRepository {


    @Override
    public List<Client> findAllClients() {
        return List.of();
    }

    @Override
    public Client saveClient(Client client) {
        return Client.builder()
                .id(3L)
                .firstName("terry")
                .lastName("Harmon")
                .build();
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteClientById(Long id) {

    }
}
