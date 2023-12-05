package com.fr.exalt.infra.mapper;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.infra.entity.AccountEntity;
import com.fr.exalt.infra.entity.ClientEntity;

public class AccountMapper {
    public static Account mapperFromAccountEntity(AccountEntity accountEntity) {
        Client client = ClientMapper.mapperFromClientEntity(accountEntity.getClientEntity());
        return Account.builder()
                .client(client)
                .creationDateTime(accountEntity.getCreationDateTime())
                .balance(accountEntity.getBalance())
                .id(accountEntity.getId())
                .build();
    }

    public static AccountEntity mapperToAccountEntity(Account account) {
        ClientEntity clientEntity = ClientMapper.mapperToClientEntity(account.getClient());
        return AccountEntity.builder()
                .clientEntity(clientEntity)
                .creationDateTime(account.getCreationDateTime())
                .balance(account.getBalance())
                .id(account.getId())
                .build();
    }

}
