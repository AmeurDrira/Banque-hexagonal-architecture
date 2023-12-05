package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.exception.NotFoundClientException;
import com.fr.exalt.core.fake.ClientRepositoryFake;
import com.fr.exalt.core.fake.ClientRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindClientUseCaseTest {

    @Test
    void return_list_client_when_find_all_clients() {
        var findClientUseCase = new FindClientUseCase(new ClientRepositoryFake());
        var result = findClientUseCase.findAllClients();
        assertEquals(3, result.size());
    }

    @Test
    void return_client_when_find_client_by_id() {
        var findClientUseCase = new FindClientUseCase(new ClientRepositoryFake());
        var result = findClientUseCase.findClientById(3L);
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("terry", result.getFirstName());
        assertEquals("Harmon", result.getLastName());
    }

    @Test
    void throw_not_found_client_exception_when_find_client_by_id() {
        var findClientUseCase = new FindClientUseCase(new ClientRepositoryNotFoundFake());
        assertThrows(NotFoundClientException.class, () -> findClientUseCase.findClientById(300L));
    }
}