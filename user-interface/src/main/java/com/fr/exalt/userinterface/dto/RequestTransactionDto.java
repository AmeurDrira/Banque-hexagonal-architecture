package com.fr.exalt.userinterface.dto;

import java.math.BigDecimal;

import com.fr.exalt.core.domain.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransactionDto {

    @NotNull
    private Long accountReceiverId;
    private BigDecimal amount;
    private TransactionType type;
}
