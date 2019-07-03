package ru.smartsoft.shop.model.service;

import ru.smartsoft.shop.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> create(User user);

    Optional<User> getByLogin();

    Optional<User> getCurrentUser();

    Optional<User> getById(long id);
}
