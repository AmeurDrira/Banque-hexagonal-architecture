package com.fr.exalt.core.port.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedCreationAccountException;
import com.fr.exalt.core.fake.AccountRepositoryFake;
import com.fr.exalt.core.fake.ClientRepositoryFake;
import com.fr.exalt.core.fake.ClientRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountUseCaseTest {

    private static final LocalDateTime CREATE_ACCOUNT_DATE = LocalDateTime.of(2023, 11, 29, 14, 30);

    @Test
    void should_return_account_when_create_account() {
        var account = buildAccount(1L);
        var createAccountUseCase = new CreateAccountUseCase(new AccountRepositoryFake(), new ClientRepositoryFake());
        var result = createAccountUseCase.createAccount(account);
        assertNotNull(result.getId());
        assertEquals(CREATE_ACCOUNT_DATE, result.getCreationDateTime());

    }

    @Test
    void should_throw_failed_creation_core_exception_when_create_account() {
        var account = buildAccount(33333L);
        var createAccountUseCase = new CreateAccountUseCase(new AccountRepositoryFake(), new ClientRepositoryNotFoundFake());
        assertThrows(FailedCreationAccountException.class, () -> createAccountUseCase.createAccount(account));
    }

    private static Account buildAccount(final Long clientId) {
        return Account.builder()
                .balance(BigDecimal.ZERO)
                .client(Client.builder().id(clientId).build())
                .creationDateTime(CREATE_ACCOUNT_DATE)
                .build();
    }
}