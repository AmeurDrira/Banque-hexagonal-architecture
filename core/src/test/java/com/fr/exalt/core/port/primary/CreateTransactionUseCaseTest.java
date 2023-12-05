package com.fr.exalt.core.port.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.core.exception.FailedCreationTransactionException;
import com.fr.exalt.core.exception.OverdraftTransactionException;
import com.fr.exalt.core.fake.AccountRepositoryFake;
import com.fr.exalt.core.fake.TransactionRepositoryDepositFake;
import com.fr.exalt.core.fake.TransactionRepositoryFake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateTransactionUseCaseTest {

    private static final String ERVAN = "ervan";
    private static final String SALIN = "salin";

    @Test
    void return_transaction_when_create_transaction_withdrawal() {
        var requestTransaction = Transaction.builder()
                .accountId(1L)
                .transactionType(TransactionType.RETRAIT)
                .transactionAmount(BigDecimal.valueOf(200))
                .build();
        var createTransactionUseCase = new CreateTransactionUseCase(new TransactionRepositoryFake(),
                new FindAccountUseCase(new AccountRepositoryFake()),
                new UpdateAccountUseCase(new AccountRepositoryFake()));
        var result = createTransactionUseCase.createTransaction(requestTransaction);
        assertNotNull(result);
        assertEquals(2L, result.getTransactionId());
        assertEquals(1L, result.getClientId());
        assertEquals(1L, result.getAccountId());
        assertEquals(ERVAN, result.getClientFirstName());
        assertEquals(SALIN, result.getClientLastName());
        assertEquals(TransactionType.RETRAIT, result.getTransactionType());
        assertEquals(BigDecimal.valueOf(200), result.getTransactionAmount());
        assertEquals(BigDecimal.valueOf(1250), result.getTransactionBalanceAfter());
        assertEquals(LocalDateTime.of(2023, 12, 4, 14, 30, 0), result.getTransactionDateTime());
    }

    @Test
    void return_transaction_when_create_transaction_deposit() {
        var requestTransaction = Transaction.builder()
                .accountId(1L)
                .clientId(1L)
                .transactionType(TransactionType.DEPOT)
                .transactionAmount(BigDecimal.valueOf(100))
                .build();
        var createTransactionUseCase = new CreateTransactionUseCase(new TransactionRepositoryDepositFake(),
                new FindAccountUseCase(new AccountRepositoryFake()),
                new UpdateAccountUseCase(new AccountRepositoryFake()));
        var result = createTransactionUseCase.createTransaction(requestTransaction);
        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        assertEquals(1L, result.getClientId());
        assertEquals(1L, result.getAccountId());
        assertEquals(ERVAN, result.getClientFirstName());
        assertEquals(SALIN, result.getClientLastName());
        assertEquals(TransactionType.DEPOT, result.getTransactionType());
        assertEquals(BigDecimal.valueOf(100), result.getTransactionAmount());
        assertEquals(BigDecimal.valueOf(1450), result.getTransactionBalanceAfter());
        assertEquals(LocalDateTime.of(2023, 12, 1, 10, 30, 0), result.getTransactionDateTime());
    }

    @Test
    void throw_transaction_core_exception_when_create_transaction_withdrawal() {
        var requestTransaction = Transaction.builder()
                .accountId(1L)
                .transactionType(TransactionType.RETRAIT)
                .transactionAmount(BigDecimal.valueOf(20000))
                .build();
        var createTransactionUseCase = new CreateTransactionUseCase(new TransactionRepositoryFake(),
                new FindAccountUseCase(new AccountRepositoryFake()),
                new UpdateAccountUseCase(new AccountRepositoryFake()));
        assertThrows(OverdraftTransactionException.class, () -> createTransactionUseCase.createTransaction(requestTransaction));
    }
}