package com.fr.exalt.userinterface.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private Long accountId;
    private Long transactionId;
    private TransactionType transactionType;
    private BigDecimal transactionAmount;
    private BigDecimal transactionBalanceAfter;
    private LocalDateTime transactionDateTime;
}
