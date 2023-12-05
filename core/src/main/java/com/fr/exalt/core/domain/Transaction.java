package com.fr.exalt.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private Long accountId;
    private LocalDateTime accountCreationDateTime;
    private Long transactionId;
    private TransactionType transactionType;
    private BigDecimal transactionAmount;
    private BigDecimal transactionBalanceAfter;
    private LocalDateTime transactionDateTime;
}
