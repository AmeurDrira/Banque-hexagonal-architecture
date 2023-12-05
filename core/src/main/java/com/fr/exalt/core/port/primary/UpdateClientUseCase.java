package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedUpdateClientException;
import com.fr.exalt.core.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public Client updateClient(final Client client) {
        if ((null == client.getId()) || clientRepository.findClientById(client.getId()).isEmpty()) {
            throw new FailedUpdateClientException(String.format("client %s not exist.", client.getId()));
        }
        return clientRepository.saveClient(client);
    }
}
