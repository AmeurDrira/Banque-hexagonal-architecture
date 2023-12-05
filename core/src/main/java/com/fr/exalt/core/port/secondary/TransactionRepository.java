package com.fr.exalt.core.port.secondary;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Transaction;

public interface TransactionRepository {

    Optional<Transaction> findTransactionById(Long id);

    List<Transaction> findAllTransactionsByClient(Long clientId);

    Transaction saveTransaction(Transaction transaction);
}
