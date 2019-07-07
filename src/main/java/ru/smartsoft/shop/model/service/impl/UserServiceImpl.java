package ru.smartsoft.shop.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.repository.UserRepository;
import ru.smartsoft.shop.model.service.OrderService;
import ru.smartsoft.shop.model.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public Optional<User> create(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getBestBuyingPersonForLastHalfYear() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp purchaseDate = Timestamp.valueOf(todayMidnight.minusMonths(6));
        List<Order> orders = orderService.getOrdersAfterDate(purchaseDate);
        Map<User, Long> countOrdersForUser = orders.stream().collect(Collectors.groupingBy(Order::getUser, Collectors.counting()));
        if (countOrdersForUser.isEmpty())
            return Optional.empty();
        else
            return Optional.of(countOrdersForUser.entrySet().stream()
                    .max(Comparator.comparing(userLongEntry -> userLongEntry.getValue())).get().getKey());
    }
}
