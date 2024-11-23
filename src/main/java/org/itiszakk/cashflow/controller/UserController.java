package org.itiszakk.cashflow.controller;

import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.domain.user.UserInput;
import org.itiszakk.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @QueryMapping(value = "users")
    public List<User> users() {
        return userService.getAll();
    }

    @QueryMapping(value = "user")
    public User user(@Argument(value = "login") String login) {
        return userService.getByLogin(login);
    }

    @MutationMapping(value = "upsertUser")
    public User upsert(@Argument(value = "userInput") UserInput input) {
        return userService.upsert(input);
    }

    @MutationMapping(value = "authenticate")
    public String authenticate(@Argument(value = "login") String login,
                                @Argument(value = "password") String password) {
        return userService.authenticate(login, password);
    }
}