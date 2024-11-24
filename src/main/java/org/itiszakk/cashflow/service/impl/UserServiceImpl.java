package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.repository.UserEntity;
import org.itiszakk.cashflow.controller.UserInput;
import org.itiszakk.cashflow.exception.impl.UserNotFoundException;
import org.itiszakk.cashflow.repository.UserRepository;
import org.itiszakk.cashflow.security.TokenProvider;
import org.itiszakk.cashflow.service.UserService;
import org.itiszakk.cashflow.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream()
                .map(UserUtils::convert)
                .toList();
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findById(login)
                .map(UserUtils::convert)
                .orElseThrow(() -> new UserNotFoundException(login));
    }

    @Override
    public User upsert(UserInput input) {

        UserEntity entity = UserEntity.builder()
                .login(input.getLogin())
                .password(passwordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .build();

        userRepository.save(entity);

        return UserUtils.convert(entity);
    }

    @Override
    public String authenticate(String login, String password) {

        User user = getByLogin(login);

        return user != null && passwordEncoder.matches(password, user.getPassword())
                ? tokenProvider.generate(user)
                : null;
    }
}