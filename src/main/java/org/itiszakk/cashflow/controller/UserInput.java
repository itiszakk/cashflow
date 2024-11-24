package org.itiszakk.cashflow.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class UserInput {

    private final String login;
    private final String password;
    private final String name;
}
