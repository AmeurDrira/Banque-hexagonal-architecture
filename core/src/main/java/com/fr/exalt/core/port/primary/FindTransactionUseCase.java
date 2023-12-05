package com.fr.exalt.core.port.primary;

import java.util.List;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.exception.NotFoundTransactionException;
import com.fr.exalt.core.port.secondary.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactionsByClient(Long clientId) {
        return transactionRepository.findAllTransactionsByClient(clientId);
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findTransactionById(transactionId)
                .orElseThrow(() -> new NotFoundTransactionException(String.format("Transaction %s not exist.", transactionId)));
    }
}
