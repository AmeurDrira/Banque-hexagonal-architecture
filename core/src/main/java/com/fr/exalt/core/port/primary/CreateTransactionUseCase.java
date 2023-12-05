package com.fr.exalt.core.port.primary;

import com.fr.exalt.core.domain.Account;
import com.fr.exalt.core.domain.Transaction;
import com.fr.exalt.core.port.secondary.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final FindAccountUseCase findAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    public Transaction createTransaction(final Transaction transactionReq) {
        final Account accountReceiver = findAccountUseCase.findAccountById(transactionReq.getAccountId());
        transactionReq.setClientId(accountReceiver.getClient().getId());
        transactionReq.setClientFirstName(accountReceiver.getClient().getFirstName());
        transactionReq.setClientLastName(accountReceiver.getClient().getLastName());
        transactionReq.setAccountCreationDateTime(accountReceiver.getCreationDateTime());
        switch (transactionReq.getTransactionType()) {
            case DEPOT -> depositTransaction(transactionReq, accountReceiver);
            case RETRAIT -> withdrawalTransaction(transactionReq, accountReceiver);
        }
        transactionReq.setTransactionBalanceAfter(accountReceiver.getBalance());
        return transactionRepository.saveTransaction(transactionReq);
    }

    private void depositTransaction(final Transaction transactionReq, final Account accountReceiver) {
        accountReceiver.deposit(transactionReq.getTransactionAmount());
        updateAccountUseCase.updateAccount(accountReceiver);
    }

    private void withdrawalTransaction(final Transaction transactionReq, final Account accountReceiver) {
        accountReceiver.withdrawal(transactionReq.getTransactionAmount());
        updateAccountUseCase.updateAccount(accountReceiver);
    }

}
