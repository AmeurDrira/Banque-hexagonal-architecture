package com.fr.exalt.core.port.primary;

import java.util.List;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.NotFoundClientException;
import com.fr.exalt.core.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindClientUseCase {

    private final ClientRepository clientRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAllClients();
    }

    public Client findClientById(Long clientId) {
        return clientRepository.findClientById(clientId)
                .orElseThrow(() -> new NotFoundClientException(String.format("client %s not exist.", clientId)));
    }

}
