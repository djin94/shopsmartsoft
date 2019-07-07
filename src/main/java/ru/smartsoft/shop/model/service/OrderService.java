package ru.smartsoft.shop.model.service;

import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> create(Order order);

    Optional<Order> update(Order order);

    Optional<Order> getById(long id);

    List<Order> getByUser(User user);

    void deleteById(long id);

    List<Order> getOrdersAfterDate(Timestamp date);

    List<Order> getOrdersForLastWeek();

    List<Order> getOrdersForLastMonth();

    List<Order> getOrdersByAgeOfPerson(int age);
}
