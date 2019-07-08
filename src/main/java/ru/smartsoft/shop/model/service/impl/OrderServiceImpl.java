package ru.smartsoft.shop.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartsoft.shop.model.entity.Item;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.repository.ItemRepository;
import ru.smartsoft.shop.model.repository.OrderRepository;
import ru.smartsoft.shop.model.service.ItemService;
import ru.smartsoft.shop.model.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public Optional<Order> create(Order order) {
        order.getBuyItems().forEach(buyItem -> {
            Item item = itemService.getById(buyItem.getItem().getId()).get();
            item.setCount(item.getCount() - buyItem.getCount());
            buyItem.setItem(itemService.update(item));
        });
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public Optional<Order> update(Order order) {
        deleteById(order.getId());
        return create(order);
    }

    @Override
    public Optional<Order> getById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void deleteById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().getBuyItems().forEach(buyItem -> {
                Item item = buyItem.getItem();
                item.setCount(item.getCount() + buyItem.getCount());
            });
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrdersAfterDate(Timestamp date) {
        return orderRepository.findByPurchaseDateAfter(date);
    }

    @Override
    public List<Order> getOrdersForLastWeek() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp startDate = Timestamp.valueOf(todayMidnight.minusDays(7));
        return orderRepository.findByPurchaseDateAfter(startDate);
    }

    @Override
    public List<Order> getOrdersForLastMonth() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp startDate = Timestamp.valueOf(todayMidnight.minusMonths(1));
        return orderRepository.findByPurchaseDateAfter(startDate);
    }

    @Override
    public List<Order> getOrdersByAgeOfPerson(int age) {
        return orderRepository.findByUserAge(age);
    }
}
