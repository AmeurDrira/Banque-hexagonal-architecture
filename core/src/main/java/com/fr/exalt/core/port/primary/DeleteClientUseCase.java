package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteClientUseCase {

    private final ClientRepository clientRepository;

    public void deleteById(Long id) {
        clientRepository.deleteClientById(id);
    }
}
