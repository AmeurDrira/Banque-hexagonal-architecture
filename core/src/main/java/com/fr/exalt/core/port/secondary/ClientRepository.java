package com.fr.exalt.core.port.secondary;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Client;

public interface ClientRepository {

    List<Client> findAllClients();

    Client saveClient(Client client);

    Optional<Client> findClientById(Long id);

    void deleteClientById(Long id);

}
