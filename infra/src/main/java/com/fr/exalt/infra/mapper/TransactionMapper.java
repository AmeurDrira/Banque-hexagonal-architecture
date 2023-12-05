package com.fr.exalt.infra.mapper;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.infra.entity.AccountEntity;
import com.fr.exalt.infra.entity.ClientEntity;
import com.fr.exalt.infra.entity.TransactionEntity;

public class TransactionMapper {
    public static Transaction mapperFromTransactionEntity(final TransactionEntity transactionEntity) {
        return Transaction.builder()
                .clientId(transactionEntity.getClient().getId())
                .clientFirstName(transactionEntity.getClient().getFirstName())
                .clientLastName(transactionEntity.getClient().getLastName())
                .accountId(transactionEntity.getAccount().getId())
                .accountCreationDateTime(transactionEntity.getAccount().getCreationDateTime())
                .transactionId(transactionEntity.getId())
                .transactionAmount(transactionEntity.getAmount())
                .transactionType(transactionEntity.getType())
                .transactionBalanceAfter(transactionEntity.getBalanceAfter())
                .transactionDateTime(transactionEntity.getDateTime())
                .build();
    }

    public static TransactionEntity mapperToTransactionEntity(final Transaction transaction) {
        return TransactionEntity.builder()
                .client(ClientEntity.builder()
                        .id(transaction.getClientId())
                        .firstName(transaction.getClientFirstName())
                        .lastName(transaction.getClientLastName())
                        .build())
                .account(AccountEntity.builder()
                        .id(transaction.getAccountId())
                        .creationDateTime(transaction.getAccountCreationDateTime())
                        .balance(transaction.getTransactionBalanceAfter())
                        .build())
                .amount(transaction.getTransactionAmount())
                .type(transaction.getTransactionType())
                .balanceAfter(transaction.getTransactionBalanceAfter())
                .dateTime(transaction.getTransactionDateTime())
                .id(transaction.getTransactionId())
                .build();
    }

}
