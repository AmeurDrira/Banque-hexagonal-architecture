package com.fr.exalt.core.port.secondary;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Account;

public interface AccountRepository {

    List<Account> findAccountsByClientId(Long clientId);

    Account saveAccount(Account account);

    Optional<Account> findAccountById(Long id);

    void deleteAccountById(Long id);

}
