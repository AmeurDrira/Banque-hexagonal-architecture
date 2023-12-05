package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.fake.ClientRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateClientUseCaseTest {

    @Test
    void should_return_client_when_create_client() {
        var client = Client.builder()
                .firstName("terry")
                .lastName("Harmon")
                .build();
        var createAccountUseCase = new CreateClientUseCase(new ClientRepositoryNotFoundFake());
        var result = createAccountUseCase.createClient(client);
        assertNotNull(result.getId());
        assertEquals("terry", result.getFirstName());
        assertEquals("Harmon", result.getLastName());
    }
}