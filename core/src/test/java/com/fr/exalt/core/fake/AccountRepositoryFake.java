package com.fr.exalt.core.fake;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.port.secondary.AccountRepository;

public class AccountRepositoryFake implements AccountRepository {

    @Override
    public List<Account> findAccountsByClientId(Long clientId) {
        return List.of(Account.builder()
                        .id(1L)
                        .balance(BigDecimal.valueOf(1350))
                        .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                        .client(Client.builder()
                                .id(1L)
                                .firstName("ervan")
                                .lastName("salin")
                                .build())
                        .build(),
                Account.builder()
                        .id(2L)
                        .balance(BigDecimal.valueOf(1500))
                        .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                        .client(Client.builder()
                                .id(1L)
                                .firstName("ervan")
                                .lastName("salin")
                                .build())
                        .build());
    }

    @Override
    public Account saveAccount(Account account) {
        return Account.builder()
                .id(1L)
                .balance(account.getBalance())
                .creationDateTime(account.getCreationDateTime())
                .client(account.getClient())
                .build();
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return Optional.of(Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(1350))
                .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                .client(Client.builder()
                        .id(1L)
                        .firstName("ervan")
                        .lastName("salin")
                        .build())
                .build());
    }

    @Override
    public void deleteAccountById(Long id) {

    }
}
