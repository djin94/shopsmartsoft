package ru.smartsoft.shop.model.service;

import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> create(Order order);

    Optional<Order> update(Order order);

    Optional<Order> getById(Order order);

    List<Order> getByUser(User user);

    void delete(Order order);

    List<Order> getOrdersForLastWeek();
}
