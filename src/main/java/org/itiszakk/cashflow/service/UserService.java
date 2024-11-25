package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.controller.UserInput;
import org.itiszakk.cashflow.domain.User;

public interface UserService {

    User getByLogin(String login);

    User create(UserInput input);

    User update(UserInput input);

    String authenticate(String login, String password);
}
