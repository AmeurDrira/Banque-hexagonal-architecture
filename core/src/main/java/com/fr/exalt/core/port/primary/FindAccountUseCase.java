package com.fr.exalt.core.port.primary;

import java.util.List;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.exception.NotFoundAccountException;
import com.fr.exalt.core.port.secondary.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAccountUseCase {

    private final AccountRepository accountRepository;

    public List<Account> findAccountByClientId(Long clientId) {
        return accountRepository.findAccountsByClientId(clientId);
    }

    public Account findAccountById(Long accountId) {
        return accountRepository.findAccountById(accountId)
                .orElseThrow(() -> new NotFoundAccountException(String.format("Account id %s not exist", accountId)));
    }

}
