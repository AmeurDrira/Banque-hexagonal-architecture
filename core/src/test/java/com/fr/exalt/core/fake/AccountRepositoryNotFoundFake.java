package com.fr.exalt.core.fake;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.port.secondary.AccountRepository;

public class AccountRepositoryNotFoundFake implements AccountRepository {

    @Override
    public List<Account> findAccountsByClientId(Long clientId) {
        return Collections.emptyList();
    }

    @Override
    public Account saveAccount(Account account) {
        return null;
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteAccountById(Long id) {

    }
}
