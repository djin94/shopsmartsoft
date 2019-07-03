package ru.smartsoft.shop.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.repository.OrderRepository;
import ru.smartsoft.shop.model.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<Order> create(Order order) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(Order order) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> getById(Order order) {
        return Optional.empty();
    }

    @Override
    public List<Order> getByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public List<Order> getOrdersForLastWeek() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp endDate = Timestamp.valueOf(todayMidnight);
        Timestamp startDate = Timestamp.valueOf(todayMidnight.minusDays(7));
        return orderRepository.findByPeriod(startDate, endDate);
    }
}
