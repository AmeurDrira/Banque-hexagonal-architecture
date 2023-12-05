package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.port.secondary.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAccountUseCase {

    private final AccountRepository accountRepository;

    public void deleteAccountById(Long id) {
        accountRepository.deleteAccountById(id);
    }
}
