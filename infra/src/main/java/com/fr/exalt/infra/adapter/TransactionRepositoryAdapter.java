package com.fr.exalt.infra.adapter;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.port.secondary.TransactionRepository;
import com.fr.exalt.infra.mapper.TransactionMapper;
import com.fr.exalt.infra.repository.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {
    private final TransactionJpaRepository transactiontJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findTransactionById(Long id) {
        return transactiontJpaRepository.findById(id)
                .map(TransactionMapper::mapperFromTransactionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllTransactionsByClient(Long clientId) {
        return transactiontJpaRepository.findTransactionEntitiesByClient_Id(clientId)
                .stream().map(TransactionMapper::mapperFromTransactionEntity)
                .toList();
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        var transactionEntity = TransactionMapper.mapperToTransactionEntity(transaction);
        var transactionEntitySaved = transactiontJpaRepository.save(transactionEntity);
        return TransactionMapper.mapperFromTransactionEntity(transactionEntitySaved);
    }
}
