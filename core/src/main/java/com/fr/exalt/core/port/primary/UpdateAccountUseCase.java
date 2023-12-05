package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.exception.FailedUpdateAccountException;
import com.fr.exalt.core.port.secondary.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    public Account updateAccount(final Account account) {
        if ((null == account.getId()) || accountRepository.findAccountById(account.getId()).isEmpty()) {
            throw new FailedUpdateAccountException(String.format("client %s not exist.", account.getId()));
        }
        return accountRepository.saveAccount(account);
    }
}
