package ru.smartsoft.shop.model.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ru.smartsoft.shop.model.entity.BuyItem;
import ru.smartsoft.shop.model.entity.Item;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.repository.UserRepository;
import ru.smartsoft.shop.model.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserServiceImpl userService;

    private Order order;

    private List<Order> orders;

    private User user;

    private Timestamp date;

    private static final long ID = 1L;

    @Before
    public void setUp() {
        user = new User();
        user.setId(ID);
        user.setLogin("test");

        Item item = new Item();
        item.setId(1L);

        BuyItem buyItem = new BuyItem();
        buyItem.setId(1L);
        buyItem.setItem(item);

        date = Timestamp.valueOf("2019-07-04 18:52:01.689");

        order = new Order();
        order.setId(1L);
        order.getBuyItems().add(buyItem);
        order.setUser(user);

        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void whenCreateUser_thenReturnUser() {
        when(userRepository.save(user)).thenReturn(user);
        Optional<User> expectedUser = Optional.of(user);

        Optional<User> actualUser = userService.create(user);

        assertEquals(expectedUser, actualUser);
        verify(userRepository).save(user);
    }

    @Test
    public void whenGetUserByLogin_thenReturnUser() {
        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        Optional<User> expectedUser = Optional.of(user);

        Optional<User> actualUser = userService.getByLogin(user.getLogin());

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void whenGetUserById_thenReturnUser() {
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        Optional<User> expectedUser = Optional.of(user);

        Optional<User> actualUser = userService.getById(ID);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void whenGetBestBuyingPersonForLastHalfYear_thenReturnUser() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp purchaseDate = Timestamp.valueOf(todayMidnight.minusMonths(6));
        when(orderService.getOrdersAfterDate(purchaseDate)).thenReturn(orders);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> expectedUser = Optional.of(user);

        Optional<User> actualUser = userService.getBestBuyingPersonForLastHalfYear();

        assertEquals(expectedUser, actualUser);
    }
}