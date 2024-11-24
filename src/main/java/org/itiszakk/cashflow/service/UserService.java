package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.controller.UserInput;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByLogin(String login);
    
    User upsert(UserInput input);

    String authenticate(String login, String password);
}
