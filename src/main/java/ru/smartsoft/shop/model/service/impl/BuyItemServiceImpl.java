package ru.smartsoft.shop.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartsoft.shop.model.entity.BuyItem;
import ru.smartsoft.shop.model.entity.Item;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.service.BuyItemService;
import ru.smartsoft.shop.model.service.OrderService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuyItemServiceImpl implements BuyItemService {

    @Autowired
    private OrderService orderService;

    @Override
    public BuyItem getBestSellingItemForLastMonth() {
        Map<Item, Integer> countItem = getCountOfItem(orderService.getOrdersForLastMonth());
        BuyItem buyItem = new BuyItem();
        buyItem.setItem(countItem.keySet().stream().max(Comparator.comparing(countItem::get)).get());
        buyItem.setCount(countItem.get(buyItem.getItem()));
        buyItem.setId(buyItem.getItem().getId());
        return buyItem;
    }

    @Override
    public List<BuyItem> getBuyItemsForLastWeek() {
        return orderService.getOrdersForLastWeek().stream()
                .collect(ArrayList::new, (buyItem, order) -> buyItem.addAll(order.getBuyItems()), ArrayList::addAll);
    }

    @Override
    public List<BuyItem> getBestSellingItemsBy18YearsOldPerson() {
        List<BuyItem> buyItems = new ArrayList<>();
        for (Map.Entry<Item, Integer> pair : getCountOfItem(orderService.getOrdersByAgeOfPerson(18)).entrySet()) {
            BuyItem buyItem = new BuyItem();
            buyItem.setId(pair.getKey().getId());
            buyItem.setItem(pair.getKey());
            buyItem.setCount(pair.getValue());
            buyItems.add(buyItem);
        }
        buyItems.sort(Comparator.comparing(BuyItem::getCount).reversed());
        return buyItems;
    }

    private Map<Item, Integer> getCountOfItem(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getBuyItems().stream()
                        .collect(Collectors.groupingBy(BuyItem::getItem, Collectors.summarizingInt(BuyItem::getCount))))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(o -> o.getKey(), Collectors.summingInt(value -> (int) value.getValue().getSum())));
    }
}
