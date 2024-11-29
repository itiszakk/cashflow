package org.itiszakk.cashflow.exception;

import graphql.ErrorClassification;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class CashflowException extends RuntimeException {

    private final ErrorClassification type;

    public CashflowException(ErrorClassification type, String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments));
        this.type = type;
    }
}