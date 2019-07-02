package ru.smartsoft.shop.model.service.impl;

import org.springframework.stereotype.Service;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> create(User user) {
        return null;
    }

    @Override
    public Optional<User> getByLogin() {
        return null;
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.empty();
    }
}
