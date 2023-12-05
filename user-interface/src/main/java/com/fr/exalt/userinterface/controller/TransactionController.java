package com.fr.exalt.userinterface.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.exception.FailedCreationTransactionException;
import com.fr.exalt.core.exception.NotFoundTransactionException;
import com.fr.exalt.core.exception.OverdraftTransactionException;
import com.fr.exalt.core.port.primary.CreateTransactionUseCase;
import com.fr.exalt.core.port.primary.FindTransactionUseCase;
import com.fr.exalt.userinterface.dto.RequestTransactionDto;
import com.fr.exalt.userinterface.exception.NotFoundException;
import com.fr.exalt.userinterface.exception.TransactionException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
@Tag(name = "Transaction", description = "Endpoint exposé pour gérer les transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindTransactionUseCase findTransactionUseCase;

    @GetMapping("/client/{clientId}")
    public List<Transaction> getAllTransactionsByClient(@PathVariable final Long clientId) {
        return findTransactionUseCase.getAllTransactionsByClient(clientId);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable final Long transactionId) {
        try {
            return findTransactionUseCase.getTransactionById(transactionId);
        } catch (NotFoundTransactionException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody final RequestTransactionDto requestDto) {
        try {
            return createTransactionUseCase.createTransaction(Transaction.builder()
                    .transactionDateTime(LocalDateTime.now())
                    .accountId(requestDto.getAccountReceiverId())
                    .transactionDateTime(LocalDateTime.now())
                    .transactionAmount(requestDto.getAmount())
                    .transactionType(requestDto.getType())
                    .build());
        } catch (FailedCreationTransactionException | OverdraftTransactionException e) {
            throw new TransactionException(e.getMessage());
        }
    }
}
