package org.itiszakk.cashflow.controller;

import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.service.UserService;
import org.itiszakk.cashflow.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public User user() {
        return userService.getByLogin(SecurityUtils.getCurrentUser());
    }

    @MutationMapping(value = "authenticate")
    public String authenticate(@Argument(value = "login") String login,
                               @Argument(value = "password") String password) {
        return userService.authenticate(login, password);
    }

    @MutationMapping(value = "createUser")
    public User create(@Argument(value = "userInput") UserInput input) {
        return userService.create(input);
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping(value = "updateUser")
    public User update(@Argument(value = "userInput") UserInput input) {
        return userService.update(input);
    }
}