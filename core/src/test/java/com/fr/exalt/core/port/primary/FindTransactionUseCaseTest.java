package com.fr.exalt.core.port.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.core.exception.NotFoundTransactionException;
import com.fr.exalt.core.fake.TransactionRepositoryFake;
import com.fr.exalt.core.fake.TransactionRepositoryNotFoundFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindTransactionUseCaseTest {

    @Test
    void return_list_transaction_dto_when_get_all_transactions_by_client_id() {
        var findTransactionUseCase = new FindTransactionUseCase(new TransactionRepositoryFake());
        var result = findTransactionUseCase.getAllTransactionsByClient(1L);
        assertEquals(3, result.size());
    }

    @Test
    void return_transaction_dto_when_get_transaction_by_id() {
        var findTransactionUseCase = new FindTransactionUseCase(new TransactionRepositoryFake());
        var result = findTransactionUseCase.getTransactionById(3L);
        assertNotNull(result);
        assertEquals(3L, result.getTransactionId());
        assertEquals(1L, result.getClientId());
        assertEquals(1L, result.getAccountId());
        assertEquals("ervan", result.getClientFirstName());
        assertEquals("salin", result.getClientLastName());
        assertEquals(TransactionType.RETRAIT, result.getTransactionType());
        assertEquals(BigDecimal.valueOf(200), result.getTransactionAmount());
        assertEquals(BigDecimal.valueOf(1050), result.getTransactionBalanceAfter());
        assertEquals(LocalDateTime.of(2023, 12, 6, 18, 30, 0), result.getTransactionDateTime());
    }

    @Test
    void throw_not_found_core_exception_when_get_transaction_by_id() {
        var findTransactionUseCase = new FindTransactionUseCase(new TransactionRepositoryNotFoundFake());
        assertThrows(NotFoundTransactionException.class, () -> findTransactionUseCase.getTransactionById(300L));
    }
}