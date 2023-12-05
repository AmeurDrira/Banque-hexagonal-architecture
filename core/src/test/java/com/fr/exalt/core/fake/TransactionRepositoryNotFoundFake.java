package com.fr.exalt.core.fake;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.port.secondary.TransactionRepository;

public class TransactionRepositoryNotFoundFake implements TransactionRepository {

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
        return null;
    }
}
