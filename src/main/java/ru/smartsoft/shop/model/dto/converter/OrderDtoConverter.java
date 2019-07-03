package ru.smartsoft.shop.model.dto.converter;

import ru.smartsoft.shop.model.dto.OrderDto;
import ru.smartsoft.shop.model.entity.Order;

import java.util.List;

public interface OrderDtoConverter {
    OrderDto convertToDto(Order order);

    List<OrderDto> convertToDtos(List<Order> orders);
}
