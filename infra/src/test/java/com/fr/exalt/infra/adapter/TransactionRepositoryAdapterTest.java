package com.fr.exalt.infra.adapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.infra.data.Data;
import com.fr.exalt.infra.repository.TransactionJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryAdapterTest {

    @Mock
    private TransactionJpaRepository repository;

    @InjectMocks
    private TransactionRepositoryAdapter transactionRepositoryAdapter;

    @Test
    void return_transaction_dto_when_find_transaction_by_id() {
        when(repository.findById(1L)).thenReturn(Optional.of(Data.getTransactions().get(0)));
        var transactionDto = transactionRepositoryAdapter.findTransactionById(1L);
        verify(repository, times(1)).findById(1L);
        assertTrue(transactionDto.isPresent());
        assertEquals("ervan", transactionDto.get().getClientFirstName());
        assertEquals("salin", transactionDto.get().getClientLastName());
        assertEquals(BigDecimal.valueOf(510), transactionDto.get().getTransactionAmount());
        assertEquals(BigDecimal.valueOf(510), transactionDto.get().getTransactionBalanceAfter());
        assertEquals(TransactionType.DEPOT, transactionDto.get().getTransactionType());
        assertEquals(LocalDateTime.of(2023, 11, 30, 14, 30, 0), transactionDto.get().getTransactionDateTime());
    }

    @Test
    void findAllTransactionsByClient() {
        when(repository.findTransactionEntitiesByClient_Id(1L)).thenReturn(Data.getTransactions());
        var transactionList = transactionRepositoryAdapter.findAllTransactionsByClient(1L);
        verify(repository, times(1)).findTransactionEntitiesByClient_Id(1L);
        assertEquals(2, transactionList.size());

    }

    @Test
    void return_transaction_dto_when_save_Transaction() {
        when(repository.save(any())).thenReturn(Data.getTransactions().get(0));
        var transaction = transactionRepositoryAdapter.saveTransaction(Transaction.builder()
                .transactionId(1L)
                .accountId(1L)
                .transactionBalanceAfter(BigDecimal.valueOf(1350))
                .accountCreationDateTime(LocalDateTime.of(2023, 11, 29, 14, 30, 0))
                .clientId(1L)
                .clientFirstName("ervan")
                .clientLastName("salin")
                .transactionAmount(BigDecimal.valueOf(510))
                .transactionDateTime(LocalDateTime.of(2023, 11, 30, 14, 30, 0))
                .transactionType(TransactionType.DEPOT)
                .build());
        verify(repository, times(1)).save(any());
        assertEquals("ervan", transaction.getClientFirstName());
        assertEquals("salin", transaction.getClientLastName());
        assertEquals(BigDecimal.valueOf(510), transaction.getTransactionAmount());
        assertEquals(BigDecimal.valueOf(510), transaction.getTransactionBalanceAfter());
        assertEquals(TransactionType.DEPOT, transaction.getTransactionType());
        assertEquals(LocalDateTime.of(2023, 11, 30, 14, 30, 0), transaction.getTransactionDateTime());


    }
}