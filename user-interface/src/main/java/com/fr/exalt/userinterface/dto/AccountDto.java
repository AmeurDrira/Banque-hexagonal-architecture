package com.fr.exalt.userinterface.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    
    private BigDecimal balance;
    private LocalDateTime creationDateTime;
    @NotNull
    private Long clientId;
}
