package org.itiszakk.cashflow.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private final String login;
    private final String password;
    private final String name;
}