package com.fr.exalt.infra.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.infra.entity.AccountEntity;
import com.fr.exalt.infra.entity.ClientEntity;
import com.fr.exalt.infra.entity.TransactionEntity;

public class Data {

    public static List<ClientEntity> getClients() {
        return List.of(
                ClientEntity.builder()
                        .id(1L)
                        .firstName("ervan")
                        .lastName("salin")
                        .build(),
                ClientEntity.builder()
                        .id(2L)
                        .firstName("jean-paul")
                        .lastName("gaultier")
                        .build(),
                ClientEntity.builder()
                        .id(3L)
                        .firstName("terry")
                        .lastName("Harmon")
                        .build()

        );
    }

    public static List<AccountEntity> getAccounts() {
        return List.of(AccountEntity.builder()
                        .id(1L)
                        .balance(BigDecimal.valueOf(1350))
                        .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                        .clientEntity(ClientEntity.builder()
                                .id(1L)
                                .firstName("ervan")
                                .lastName("salin")
                                .build())
                        .build(),
                AccountEntity.builder()
                        .id(2L)
                        .balance(BigDecimal.valueOf(1500))
                        .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                        .clientEntity(ClientEntity.builder()
                                .id(1L)
                                .firstName("ervan")
                                .lastName("salin")
                                .build())
                        .build());
    }


    public static List<TransactionEntity> getTransactions() {
        return List.of(TransactionEntity.builder()
                        .id(1L)
                        .account(getAccounts().get(0))
                        .amount(BigDecimal.valueOf(510))
                        .client(getClients().get(0))
                        .dateTime(LocalDateTime.of(2023, 11, 30, 14, 30, 0))
                        .balanceAfter(BigDecimal.valueOf(510))
                        .type(TransactionType.DEPOT)
                        .build(),
                TransactionEntity.builder()
                        .id(2L)
                        .account(getAccounts().get(0))
                        .amount(BigDecimal.TEN)
                        .client(getClients().get(0))
                        .dateTime(LocalDateTime.of(2023, 11, 30, 14, 30, 0))
                        .balanceAfter(BigDecimal.valueOf(500))
                        .type(TransactionType.RETRAIT)
                        .build());
    }
}

