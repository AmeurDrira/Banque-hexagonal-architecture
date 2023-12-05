package com.fr.exalt.core.fake;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.core.port.secondary.TransactionRepository;

public class TransactionRepositoryFake implements TransactionRepository {

    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return Optional.of(Transaction.builder()
                .transactionId(3L)
                .transactionType(TransactionType.RETRAIT)
                .transactionAmount(BigDecimal.valueOf(200))
                .transactionDateTime(LocalDateTime.of(2023, 12, 6, 18, 30, 0))
                .transactionBalanceAfter(BigDecimal.valueOf(1050))
                .clientId(1L)
                .clientFirstName("ervan")
                .clientLastName("salin")
                .accountId(1L)
                .build());
    }

    @Override
    public List<Transaction> findAllTransactionsByClient(Long clientId) {
        return List.of(Transaction.builder()
                        .transactionId(1L)
                        .transactionType(TransactionType.DEPOT)
                        .transactionAmount(BigDecimal.valueOf(100))
                        .transactionDateTime(LocalDateTime.of(2023, 12, 1, 10, 30, 0))
                        .transactionBalanceAfter(BigDecimal.valueOf(1450))
                        .clientId(1L)
                        .clientFirstName("ervan")
                        .clientLastName("salin")
                        .accountId(1L)
                        .build(),
                Transaction.builder()
                        .transactionId(2L)
                        .transactionType(TransactionType.RETRAIT)
                        .transactionAmount(BigDecimal.valueOf(200))
                        .transactionDateTime(LocalDateTime.of(2023, 12, 4, 14, 30, 0))
                        .transactionBalanceAfter(BigDecimal.valueOf(1250))
                        .clientId(1L)
                        .clientFirstName("ervan")
                        .clientLastName("salin")
                        .accountId(1L)
                        .build(),
                Transaction.builder()
                        .transactionId(3L)
                        .transactionType(TransactionType.RETRAIT)
                        .transactionAmount(BigDecimal.valueOf(200))
                        .transactionDateTime(LocalDateTime.of(2023, 12, 6, 18, 30, 0))
                        .transactionBalanceAfter(BigDecimal.valueOf(1050))
                        .clientId(1L)
                        .clientFirstName("ervan")
                        .clientLastName("salin")
                        .accountId(1L)
                        .build()
        );
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return Transaction.builder()
                .transactionId(2L)
                .transactionType(TransactionType.RETRAIT)
                .transactionAmount(BigDecimal.valueOf(200))
                .transactionDateTime(LocalDateTime.of(2023, 12, 4, 14, 30, 0))
                .transactionBalanceAfter(BigDecimal.valueOf(1250))
                .clientId(1L)
                .clientFirstName("ervan")
                .clientLastName("salin")
                .accountId(1L)
                .build();
    }
}
