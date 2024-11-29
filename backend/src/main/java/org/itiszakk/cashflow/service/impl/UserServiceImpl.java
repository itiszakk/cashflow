package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.controller.UserInput;
import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.repository.UserEntity;
import org.itiszakk.cashflow.repository.UserRepository;
import org.itiszakk.cashflow.security.TokenProvider;
import org.itiszakk.cashflow.service.UserService;
import org.itiszakk.cashflow.util.SecurityUtils;
import org.itiszakk.cashflow.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public User getByLogin(String login) {
        return UserUtils.convert(userRepository.getReferenceById(login));
    }

    @Override
    public User create(UserInput input) {

        UserEntity entity = UserEntity.builder()
                .login(input.getLogin())
                .password(passwordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .build();

        return UserUtils.convert(userRepository.save(entity));
    }

    @Override
    public User update(UserInput input) {

        UserEntity entity = UserEntity.builder()
                .login(SecurityUtils.getCurrentUser())
                .password(passwordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .build();

        return UserUtils.convert(userRepository.save(entity));
    }

    @Override
    public String authenticate(String login, String password) {

        User user = getByLogin(login);

        return user != null && passwordEncoder.matches(password, user.getPassword())
                ? tokenProvider.generate(user)
                : null;
    }
}