package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.fake.AccountRepositoryFake;
import org.junit.jupiter.api.Test;

class DeleteAccountUseCaseTest {

    @Test
    void empty_retun_when_delete_Account_by_id() {
        var deleteAccountUseCase = new DeleteAccountUseCase(new AccountRepositoryFake());
        deleteAccountUseCase.deleteAccountById(1L);
    }
}