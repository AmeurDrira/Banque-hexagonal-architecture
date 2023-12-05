package com.fr.exalt.userinterface.controller;

import java.util.List;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.exception.FailedCreationAccountException;
import com.fr.exalt.core.exception.NotFoundAccountException;
import com.fr.exalt.core.port.primary.CreateAccountUseCase;
import com.fr.exalt.core.port.primary.DeleteAccountUseCase;
import com.fr.exalt.core.port.primary.FindAccountUseCase;
import com.fr.exalt.userinterface.dto.AccountDto;
import com.fr.exalt.userinterface.exception.FailedCreationException;
import com.fr.exalt.userinterface.exception.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Tag(name = "Account", description = "Endpoint for accounts management")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final FindAccountUseCase findAccountUseCase;

    @GetMapping("/account-by-client/{clientId}")
    public List<Account> getAllAccountByClient(@PathVariable Long clientId) {
        return findAccountUseCase.findAccountByClientId(clientId);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        try {
            return findAccountUseCase.findAccountById(accountId);
        } catch (NotFoundAccountException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public Account create(@Valid @RequestBody AccountDto accountDto) {
        try {
            return createAccountUseCase.createAccount(Account.builder()
                    .balance(accountDto.getBalance())
                    .creationDateTime(accountDto.getCreationDateTime())
                    .client(Client.builder()
                            .id(accountDto.getClientId())
                            .build())
                    .build());
        } catch (FailedCreationAccountException e) {
            throw new FailedCreationException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        deleteAccountUseCase.deleteAccountById(id);
    }

}
