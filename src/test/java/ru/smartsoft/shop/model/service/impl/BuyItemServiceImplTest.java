package ru.smartsoft.shop.model.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import ru.smartsoft.shop.model.entity.BuyItem;
import ru.smartsoft.shop.model.entity.Item;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.service.OrderService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BuyItemServiceImplTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private BuyItemServiceImpl buyItemService;

    private Order order;

    private List<Order> orders;

    private User user;

    private BuyItem buyItem;

    private Timestamp date;

    private static final long ID = 1L;

    @Before
    public void setUp() {
        user = new User();
        user.setId(ID);
        user.setLogin("test");

        Item item = new Item();
        item.setId(1L);

        buyItem = new BuyItem();
        buyItem.setId(1L);
        buyItem.setItem(item);
        buyItem.setCount(5);

        date = Timestamp.valueOf("2019-07-04 18:52:01.689");

        order = new Order();
        order.setId(1L);
        order.getBuyItems().add(buyItem);
        order.setUser(user);
        buyItem.setOrder(order);

        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void whenGetBestSellingItemForLastMonth_thenReturnBuyItem(){
        when(orderService.getOrdersForLastMonth()).thenReturn(orders);
        BuyItem expectedBuyItem = buyItem;

        BuyItem actualBuyItem = buyItemService.getBestSellingItemForLastMonth();

        assertEquals(expectedBuyItem, actualBuyItem);
    }

    @Test
    public void whenGetBuyItemsForLastWeek_thenReturnListOfBuyItems(){
        when(orderService.getOrdersForLastWeek()).thenReturn(orders);
        List<BuyItem> expectedBuyItems = order.getBuyItems();

        List<BuyItem> actualBuyItems = buyItemService.getBuyItemsForLastWeek();

        assertEquals(expectedBuyItems, actualBuyItems);
    }

    @Test
    public void whenGetBestSellingItemsBy18YearsOldPerson_thenReturnListOfBuyItem(){
        when(orderService.getOrdersByAgeOfPerson(18)).thenReturn(orders);
        List<BuyItem> expectedBuyItems = order.getBuyItems();

        List<BuyItem> actualBuyItems = buyItemService.getBestSellingItemsBy18YearsOldPerson();

        assertEquals(expectedBuyItems, actualBuyItems);
    }
}