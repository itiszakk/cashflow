package org.itiszakk.cashflow.mapper;

import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.domain.user.UserEntity;

public class UserMapper {

    public static User convert(UserEntity source) {
        return User.builder()
                .login(source.getLogin())
                .password(source.getPassword())
                .name(source.getName())
                .build();
    }

    public static UserEntity convert(User source) {
        return UserEntity.builder()
                .login(source.getLogin())
                .password(source.getPassword())
                .name(source.getName())
                .build();
    }
}
