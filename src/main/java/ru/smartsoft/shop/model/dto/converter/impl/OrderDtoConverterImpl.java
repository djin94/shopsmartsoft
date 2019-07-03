package ru.smartsoft.shop.model.dto.converter.impl;

import ru.smartsoft.shop.model.dto.OrderDto;
import ru.smartsoft.shop.model.dto.converter.OrderDtoConverter;
import ru.smartsoft.shop.model.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDtoConverterImpl implements OrderDtoConverter {
    @Override
    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setBuyItems(order.getBuyItems());
        orderDto.setUser(new UserDtoConverterImpl().convertToUserDto(order.getUser()));
        orderDto.setPurchaseDate(order.getPurchaseDate());
        orderDto.setAmount((double) order.getBuyItems().stream()
                .mapToInt(buyItem -> buyItem.getCount()*buyItem.getItem().getPrice())
                .summaryStatistics().getSum()/100);
        return orderDto;
    }

    @Override
    public List<OrderDto> convertToDtos(List<Order> orders) {
        return orders.stream().collect(ArrayList::new, (orderDtos, order) -> orderDtos.add(convertToDto(order)), ArrayList::addAll);
    }
}
