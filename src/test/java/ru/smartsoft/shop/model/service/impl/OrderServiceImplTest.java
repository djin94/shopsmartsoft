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
import ru.smartsoft.shop.model.repository.OrderRepository;
import ru.smartsoft.shop.model.service.ItemService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    private List<Order> orders;

    private User user;

    private Item item;

    private Timestamp date;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);

        item = new Item();
        item.setId(1L);
        item.setCount(20);

        BuyItem buyItem = new BuyItem();
        buyItem.setId(1L);
        buyItem.setItem(item);
        buyItem.setCount(5);

        date = Timestamp.valueOf("2019-07-04 18:52:01.689");

        order = new Order();
        order.setId(1L);
        order.getBuyItems().add(buyItem);

        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void whenCreateOrder_thenReturnOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        when(itemService.getById(item.getId())).thenReturn(Optional.of(item));
        when(itemService.update(item)).thenReturn(item);
        Optional<Order> expectedOrder = Optional.of(order);

        Optional<Order> actualOrder = orderService.create(order);

        assertEquals(expectedOrder, actualOrder);
        verify(orderRepository).save(order);
    }

    @Test
    public void whenUpdateOrder_thenReturnOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(itemService.getById(item.getId())).thenReturn(Optional.of(item));
        when(itemService.update(item)).thenReturn(item);
        doNothing().when(orderRepository).deleteById(order.getId());
        Optional<Order> expectedOrder = Optional.of(order);

        Optional<Order> actualOrder = orderService.update(order);

        assertEquals(expectedOrder, actualOrder);
        verify(orderRepository).save(order);
    }

    @Test
    public void whenGetOrderById_thenReturnOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Optional<Order> expectedOrder = Optional.of(order);

        Optional<Order> actualOrder = orderService.getById(order.getId());

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    public void whenGetOrderByUser_thenReturnListOfOrders() {
        when(orderRepository.findByUser(user)).thenReturn(orders);
        List<Order> expectedOrders = orders;

        List<Order> actualOrders = orderService.getByUser(user);

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    public void whenDeleteOrderById_thenDeleteOrder() {
        doNothing().when(orderRepository).deleteById(order.getId());
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        orderService.deleteById(order.getId());

        verify(orderRepository).deleteById(order.getId());
    }

    @Test
    public void whenGetOrdersAfterDate_thenReturnListOfOrders() {
        when(orderRepository.findByPurchaseDateAfter(date)).thenReturn(orders);
        List<Order> expectedOrders = orders;

        List<Order> actualOrders = orderService.getOrdersAfterDate(date);

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    public void whenGetOrdersForLastWeek_thenReturnListOfOrders() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp startDate = Timestamp.valueOf(todayMidnight.minusDays(7));
        when(orderRepository.findByPurchaseDateAfter(startDate)).thenReturn(orders);
        List<Order> expectedOrders = orders;

        List<Order> actualOrders = orderService.getOrdersForLastWeek();

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    public void whenGetOrdersForLastMonth_thenReturnListOfOrders() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        Timestamp startDate = Timestamp.valueOf(todayMidnight.minusMonths(1));
        when(orderRepository.findByPurchaseDateAfter(startDate)).thenReturn(orders);
        List<Order> expectedOrders = orders;

        List<Order> actualOrders = orderService.getOrdersForLastMonth();

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    public void whenGetOrdersByPersonAge_thenReturnListOfOrders() {
        when(orderRepository.findByUserAge(18)).thenReturn(orders);
        List<Order> expectedOrders = orders;

        List<Order> actualOrders = orderService.getOrdersByAgeOfPerson(18);

        assertEquals(expectedOrders, actualOrders);
    }
}