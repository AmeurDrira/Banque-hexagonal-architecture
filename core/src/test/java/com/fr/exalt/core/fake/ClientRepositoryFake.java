package com.fr.exalt.core.fake;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.port.secondary.ClientRepository;

public class ClientRepositoryFake implements ClientRepository {


    @Override
    public List<Client> findAllClients() {
        return List.of(
                Client.builder()
                        .id(1L)
                        .firstName("ervan")
                        .lastName("salin")
                        .build(),
                Client.builder()
                        .id(2L)
                        .firstName("jean-paul")
                        .lastName("gaultier")
                        .build(),
                Client.builder()
                        .id(3L)
                        .firstName("terry")
                        .lastName("Harmon")
                        .build()

        );
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
        return Optional.of(Client.builder()
                .id(3L)
                .firstName("terry")
                .lastName("Harmon")
                .build());
    }

    @Override
    public void deleteClientById(Long id) {

    }

}
