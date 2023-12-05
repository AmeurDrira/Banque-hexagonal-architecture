package com.fr.exalt.infra.adapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.infra.data.Data;
import com.fr.exalt.infra.repository.AccountJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryAdapterTest {

    @Mock
    private AccountJpaRepository repository;

    @InjectMocks
    private AccountRepositoryAdapter accountRepositoryAdapter;

    @Test
    void return_list_account_when_find_accounts_by_client_id() {
        when(repository.findAccountsByClientEntity_Id(1L)).thenReturn(Data.getAccounts());
        List<Account> accountList = accountRepositoryAdapter.findAccountsByClientId(1L);
        verify(repository, times(1)).findAccountsByClientEntity_Id(1L);
        assertEquals(2, accountList.size());
    }

    @Test
    void return_account_save_account() {
        when(repository.save(any())).thenReturn(Data.getAccounts().get(0));
        var account = accountRepositoryAdapter
                .saveAccount(Account.builder()
                        .balance(BigDecimal.valueOf(1350))
                        .creationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                        .client(Client.builder()
                                .id(1L)
                                .firstName("ervan")
                                .lastName("salin")
                                .build())
                        .build());
        verify(repository, times(1)).save(any());
        assertNotNull(account.getId());
        assertEquals("ervan", account.getClient().getFirstName());
        assertEquals("salin", account.getClient().getLastName());
        assertEquals(BigDecimal.valueOf(1350), account.getBalance());
        assertEquals(LocalDateTime.of(2023, 11, 29, 14, 30, 0), account.getCreationDateTime());

    }

    @Test
    void return_account_when_find_account_by_id() {
        when(repository.findById(2L)).thenReturn(Optional.of(Data.getAccounts().get(1)));
        var account = accountRepositoryAdapter
                .findAccountById(2L);
        verify(repository, times(1)).findById(2L);
        assertTrue(account.isPresent());
        assertEquals("ervan", account.get().getClient().getFirstName());
        assertEquals("salin", account.get().getClient().getLastName());
        assertEquals(BigDecimal.valueOf(1500), account.get().getBalance());
        assertEquals(LocalDateTime.of(2023, 11, 29, 14, 30, 0), account.get().getCreationDateTime());
    }

    @Test
    void do_not_return_when_delete_account_by_id() {
        doNothing().when(repository).deleteById(1L);
        accountRepositoryAdapter.deleteAccountById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}