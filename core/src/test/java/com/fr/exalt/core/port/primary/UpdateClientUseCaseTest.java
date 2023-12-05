package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedUpdateClientException;
import com.fr.exalt.core.fake.ClientRepositoryFake;
import com.fr.exalt.core.fake.ClientRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateClientUseCaseTest {

    @Test
    void return_client_when_update_client() {
        var client = Client.builder()
                .id(3L)
                .firstName("terry")
                .lastName("Harmon")
                .build();
        var updateClientUseCase = new UpdateClientUseCase(new ClientRepositoryFake());
        var result = updateClientUseCase.updateClient(client);
        assertNotNull(result.getId());
        assertEquals("terry", result.getFirstName());
        assertEquals("Harmon", result.getLastName());
    }

    @Test
    void throw_failed_update_core_exception_when_update_client_by_id() {
        var client = Client.builder()
                .id(33L)
                .firstName("terry-33")
                .lastName("Harmon-33")
                .build();
        var updateClientUseCase = new UpdateClientUseCase(new ClientRepositoryNotFoundFake());
        assertThrows(FailedUpdateClientException.class, () -> updateClientUseCase.updateClient(client));
    }
}