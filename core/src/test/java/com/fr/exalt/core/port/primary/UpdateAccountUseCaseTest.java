package com.fr.exalt.core.port.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedUpdateAccountException;
import com.fr.exalt.core.fake.AccountRepositoryFake;
import com.fr.exalt.core.fake.AccountRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateAccountUseCaseTest {
    private static final String ERVAN = "ervan";
    private static final String SALIN = "salin";

    @Test
    void return_account_when_update_account() {
        var account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(1350))
                .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                .client(Client.builder()
                        .id(1L)
                        .firstName(ERVAN)
                        .lastName(SALIN)
                        .build())
                .build();
        var updateAccountUseCase = new UpdateAccountUseCase(new AccountRepositoryFake());
        var result = updateAccountUseCase.updateAccount(account);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(ERVAN, result.getClient().getFirstName());
        assertEquals(SALIN, result.getClient().getLastName());
        assertEquals(BigDecimal.valueOf(1350), result.getBalance());
        assertEquals(LocalDateTime.of(2023, 11, 29, 14, 30, 0), result.getCreationDateTime());

    }

    @Test
    void throw_failed_update_core_exception_when_update_account() {
        var account = Account.builder()
                .id(1000L)
                .balance(BigDecimal.valueOf(0))
                .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                .client(Client.builder()
                        .id(1L)
                        .firstName(ERVAN)
                        .lastName(SALIN)
                        .build())
                .build();
        var updateAccountUseCase = new UpdateAccountUseCase(new AccountRepositoryNotFoundFake());
        assertThrows(FailedUpdateAccountException.class, () -> updateAccountUseCase.updateAccount(account));
    }
}