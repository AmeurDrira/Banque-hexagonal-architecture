package com.fr.exalt.userinterface.configuration;

import com.fr.exalt.core.port.primary.CreateAccountUseCase;
import com.fr.exalt.core.port.primary.CreateClientUseCase;
import com.fr.exalt.core.port.primary.CreateTransactionUseCase;
import com.fr.exalt.core.port.primary.DeleteAccountUseCase;
import com.fr.exalt.core.port.primary.DeleteClientUseCase;
import com.fr.exalt.core.port.primary.FindAccountUseCase;
import com.fr.exalt.core.port.primary.FindClientUseCase;
import com.fr.exalt.core.port.primary.FindTransactionUseCase;
import com.fr.exalt.core.port.primary.UpdateAccountUseCase;
import com.fr.exalt.core.port.primary.UpdateClientUseCase;
import com.fr.exalt.core.port.secondary.AccountRepository;
import com.fr.exalt.core.port.secondary.ClientRepository;
import com.fr.exalt.core.port.secondary.TransactionRepository;
import com.fr.exalt.infra.adapter.AccountRepositoryAdapter;
import com.fr.exalt.infra.adapter.ClientRepositoryAdapter;
import com.fr.exalt.infra.adapter.TransactionRepositoryAdapter;
import com.fr.exalt.infra.repository.AccountJpaRepository;
import com.fr.exalt.infra.repository.ClientJpaRepository;
import com.fr.exalt.infra.repository.TransactionJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInterfaceConfiguration {

    @Bean
    public TransactionRepository getTransactionRepository(TransactionJpaRepository transactionJpaRepository) {
        return new TransactionRepositoryAdapter(transactionJpaRepository);
    }

    @Bean
    public ClientRepository getClientRepository(ClientJpaRepository clientJpaRepository) {
        return new ClientRepositoryAdapter(clientJpaRepository);
    }

    @Bean
    public AccountRepository getAccountRepository(AccountJpaRepository accountJpaRepository) {
        return new AccountRepositoryAdapter(accountJpaRepository);
    }

    @Bean
    public CreateAccountUseCase getManageAccountUseCase(AccountRepository accountRepository, ClientRepository clientRepository) {
        return new CreateAccountUseCase(accountRepository, clientRepository);
    }

    @Bean
    public CreateClientUseCase getManageClientUseCase(ClientRepository clientRepository) {
        return new CreateClientUseCase(clientRepository);
    }

    @Bean
    public FindClientUseCase getFindClientUseCase(ClientRepository clientRepository) {
        return new FindClientUseCase(clientRepository);
    }

    @Bean
    public FindAccountUseCase getFindAccountUseCase(AccountRepository accountRepository) {
        return new FindAccountUseCase(accountRepository);
    }

    @Bean
    public DeleteAccountUseCase getDeleteAccountUseCase(AccountRepository accountRepository) {
        return new DeleteAccountUseCase(accountRepository);
    }

    @Bean
    public DeleteClientUseCase getDeleteClientUseCase(ClientRepository clientRepository) {
        return new DeleteClientUseCase(clientRepository);
    }

    @Bean
    public UpdateClientUseCase getUpdateClientUseCase(ClientRepository clientRepository) {
        return new UpdateClientUseCase(clientRepository);
    }

    @Bean
    public UpdateAccountUseCase getUpdateAccountUseCase(AccountRepository accountRepository) {
        return new UpdateAccountUseCase(accountRepository);
    }

    @Bean
    public CreateTransactionUseCase getCreateTransactionUseCase(TransactionRepository transactionRepository,
                                                                FindAccountUseCase findAccountUseCase,
                                                                UpdateAccountUseCase updateAccountUseCase) {
        return new CreateTransactionUseCase(transactionRepository, findAccountUseCase, updateAccountUseCase);
    }

    @Bean
    public FindTransactionUseCase getFindTransactionUseCase(TransactionRepository transactionRepository) {
        return new FindTransactionUseCase(transactionRepository);
    }
}
