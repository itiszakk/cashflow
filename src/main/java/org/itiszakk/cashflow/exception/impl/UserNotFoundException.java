package org.itiszakk.cashflow.exception.impl;

import org.itiszakk.cashflow.exception.NotFoundException;

import java.text.MessageFormat;

public class UserNotFoundException
        extends RuntimeException
        implements NotFoundException {

    public UserNotFoundException(String login) {
        super(MessageFormat.format("User with login [{0}] not found", login));
    }
}
