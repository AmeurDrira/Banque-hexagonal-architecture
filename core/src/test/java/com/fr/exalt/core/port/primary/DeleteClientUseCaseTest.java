package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.fake.ClientRepositoryFake;
import org.junit.jupiter.api.Test;

class DeleteClientUseCaseTest {

    @Test
    void empty_retun_when_delete_client_by_id() {
        var deleteClientUseCase = new DeleteClientUseCase(new ClientRepositoryFake());
        deleteClientUseCase.deleteById(1L);
    }
}