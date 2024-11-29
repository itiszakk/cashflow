package org.itiszakk.cashflow.util;

import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.repository.UserEntity;

public class UserUtils {

    public static User convert(UserEntity source) {
        return User.builder()
                .login(source.getLogin())
                .password(source.getPassword())
                .name(source.getName())
                .build();
    }
}
