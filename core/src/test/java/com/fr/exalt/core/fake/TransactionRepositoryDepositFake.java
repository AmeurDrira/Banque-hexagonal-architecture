package com.fr.exalt.core.fake;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.core.port.secondary.TransactionRepository;

public class TransactionRepositoryDepositFake implements TransactionRepository {

    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAllTransactionsByClient(Long clientId) {
        return Collections.emptyList();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return Transaction.builder()
                .transactionId(1L)
                .transactionType(TransactionType.DEPOT)
                .transactionAmount(BigDecimal.valueOf(100))
                .transactionDateTime(LocalDateTime.of(2023, 12, 1, 10, 30, 0))
                .transactionBalanceAfter(BigDecimal.valueOf(1450))
                .clientId(1L)
                .clientFirstName("ervan")
                .clientLastName("salin")
                .accountId(1L)
                .build();
    }
}
