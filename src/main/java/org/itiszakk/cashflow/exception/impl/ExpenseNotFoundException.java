package org.itiszakk.cashflow.exception.impl;

import org.itiszakk.cashflow.exception.NotFoundException;

import java.text.MessageFormat;

public class ExpenseNotFoundException
        extends RuntimeException
        implements NotFoundException {

    public ExpenseNotFoundException(Long id) {
        super(MessageFormat.format("Expense with id [{0}] not found", id));
    }
}
