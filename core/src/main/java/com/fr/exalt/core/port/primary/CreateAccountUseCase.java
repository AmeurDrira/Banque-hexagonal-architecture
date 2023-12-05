package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedCreationAccountException;
import com.fr.exalt.core.port.secondary.AccountRepository;
import com.fr.exalt.core.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public Account createAccount(final Account account) {
        Client client = clientRepository.findClientById(account.getClient().getId())
                .orElseThrow(() -> new FailedCreationAccountException(String.format("client %s not exist.", account.getClient().getId())));
        account.setClient(client);
        return accountRepository.saveAccount(account);

    }

}
