package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.domain.user.UserEntity;
import org.itiszakk.cashflow.domain.user.UserInput;
import org.itiszakk.cashflow.exception.impl.UserNotFoundException;
import org.itiszakk.cashflow.mapper.UserMapper;
import org.itiszakk.cashflow.repository.UserRepository;
import org.itiszakk.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::convert)
                .toList();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userRepository.findById(login)
                .map(UserMapper::convert);
    }

    @Override
    public User ensureUser(String login) {
        return getByLogin(login)
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

        return UserMapper.convert(entity);
    }

    @Override
    public boolean authenticate(String login, String password) {
        User user = ensureUser(login);
        return passwordEncoder.matches(password, user.getPassword());
    }
}