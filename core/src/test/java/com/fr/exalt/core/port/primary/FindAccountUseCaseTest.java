package com.fr.exalt.core.port.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.exception.NotFoundAccountException;
import com.fr.exalt.core.fake.AccountRepositoryFake;
import com.fr.exalt.core.fake.AccountRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindAccountUseCaseTest {

    private static final String ERVAN = "ervan";
    private static final String SALIN = "salin";

    @Test
    void return_list_account_when_find_account_by_client_id() {
        var findAccountUseCase = new FindAccountUseCase(new AccountRepositoryFake());
        var result = findAccountUseCase.findAccountByClientId(1L);
        assertEquals(2, result.size());
        assertEquals(ERVAN, result.get(0).getClient().getFirstName());
        assertEquals(ERVAN, result.get(1).getClient().getFirstName());
        assertEquals(SALIN, result.get(0).getClient().getLastName());
        assertEquals(SALIN, result.get(1).getClient().getLastName());

    }

    @Test
    void return_account_when_find_account_by_id() {
        var findAccountUseCase = new FindAccountUseCase(new AccountRepositoryFake());
        var result = findAccountUseCase.findAccountById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(ERVAN, result.getClient().getFirstName());
        assertEquals(SALIN, result.getClient().getLastName());
        assertEquals(BigDecimal.valueOf(1350), result.getBalance());
        assertEquals(LocalDateTime.of(2023, 11, 29, 14, 30, 0), result.getCreationDateTime());
    }

    @Test
    void throw_not_found_transaction_exception_when_find_account_by_id() {
        var findAccountUseCase = new FindAccountUseCase(new AccountRepositoryNotFoundFake());
        assertThrows(NotFoundAccountException.class, () -> findAccountUseCase.findAccountById(300L));
    }
}