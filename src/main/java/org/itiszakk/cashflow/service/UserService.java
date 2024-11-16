package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.domain.user.UserInput;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<User> getByLogin(String login);

    User ensureUser(String login);

    User upsert(UserInput input);

    boolean authenticate(String login, String password);
}
