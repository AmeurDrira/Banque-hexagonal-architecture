package com.fr.exalt.infra.adapter;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.port.secondary.AccountRepository;
import com.fr.exalt.infra.mapper.AccountMapper;
import com.fr.exalt.infra.repository.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAccountsByClientId(Long clientId) {
        return accountJpaRepository.findAccountsByClientEntity_Id(clientId)
                .stream().map(AccountMapper::mapperFromAccountEntity)
                .toList();
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {
        var accountEntity = AccountMapper.mapperToAccountEntity(account);
        var accountEntitySaved = accountJpaRepository.save(accountEntity);
        return AccountMapper.mapperFromAccountEntity(accountEntitySaved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findAccountById(Long id) {
        return accountJpaRepository.findById(id)
                .map(AccountMapper::mapperFromAccountEntity);
    }

    @Override
    @Transactional
    public void deleteAccountById(Long id) {
        accountJpaRepository.deleteById(id);

    }
}
