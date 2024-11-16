package org.itiszakk.cashflow.exception.impl;

import org.itiszakk.cashflow.exception.NotFoundException;

import java.text.MessageFormat;

public class CategoryNotFoundException
        extends RuntimeException
        implements NotFoundException {

    public CategoryNotFoundException(Long id) {
        super(MessageFormat.format("Category with id [{0}] not found", id));
    }
}