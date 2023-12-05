package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public Client createClient(final Client client) {
        return clientRepository.saveClient(client);
    }
}
