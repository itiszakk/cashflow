package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.domain.user.UserInput;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByLogin(String login);

    User upsert(UserInput input);

    boolean authenticate(String login, String password);
}
