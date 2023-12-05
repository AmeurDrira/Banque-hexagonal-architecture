package com.fr.exalt.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fr.exalt.core.exception.OverdraftTransactionException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;
    private BigDecimal balance;
    private LocalDateTime creationDateTime;
    private Client client;

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdrawal(BigDecimal amount) {
        final BigDecimal newSolde = this.balance.subtract(amount);
        if (newSolde.compareTo(BigDecimal.ZERO) < 0) {
            throw new OverdraftTransactionException(String.format("The account will be in bank overdraft %s", newSolde));
        }
        this.balance = newSolde;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
