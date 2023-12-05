package com.fr.exalt.userinterface.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class TransactionException extends AbstractThrowableProblem {

    private static final String EXCEPTION_TITLE_TRANSACTION_REFUSED = "Transaction refused";

    public TransactionException(String message) {
        super(null, EXCEPTION_TITLE_TRANSACTION_REFUSED, Status.BAD_REQUEST, message);
    }

}
