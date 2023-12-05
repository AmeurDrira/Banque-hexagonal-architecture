package com.fr.exalt.userinterface.controller;

import java.util.List;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedUpdateClientException;
import com.fr.exalt.core.exception.NotFoundClientException;
import com.fr.exalt.core.port.primary.CreateClientUseCase;
import com.fr.exalt.core.port.primary.DeleteClientUseCase;
import com.fr.exalt.core.port.primary.FindClientUseCase;
import com.fr.exalt.core.port.primary.UpdateClientUseCase;
import com.fr.exalt.userinterface.dto.ClientDto;
import com.fr.exalt.userinterface.exception.FailedUpdateException;
import com.fr.exalt.userinterface.exception.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
@Tag(name = "Client", description = "Endpoint exposé pour gérer les clients")
public class ClientController {

    private final FindClientUseCase findClientUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    @GetMapping
    public List<Client> getAllClients() {
        return findClientUseCase.findAllClients();
    }

    @GetMapping(value = "/{id}")
    public Client getClient(@PathVariable(name = "id") Long id) {
        try {
            return findClientUseCase.findClientById(id);
        } catch (NotFoundClientException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public Client create(@Valid @RequestBody ClientDto clientDto) {
        return createClientUseCase.createClient(Client.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .build());
    }

    @PutMapping
    public Client updateClient(@Valid @RequestBody Client client) {
        try {
            return updateClientUseCase.updateClient(client);
        } catch (FailedUpdateClientException e) {
            throw new FailedUpdateException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        deleteClientUseCase.deleteById(id);
    }
}
