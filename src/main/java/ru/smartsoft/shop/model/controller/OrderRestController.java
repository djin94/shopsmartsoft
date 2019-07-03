package ru.smartsoft.shop.model.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.shop.model.dto.OrderDto;
import ru.smartsoft.shop.model.dto.converter.OrderDtoConverter;
import ru.smartsoft.shop.model.dto.converter.impl.OrderDtoConverterImpl;
import ru.smartsoft.shop.model.entity.Order;
import ru.smartsoft.shop.model.service.OrderService;
import ru.smartsoft.shop.model.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(OrderRestController.URL)
public class OrderRestController {
    public static final String URL = "/orders";

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private OrderDtoConverter orderDtoConverter = new OrderDtoConverterImpl();

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public void saveOrder(@RequestBody Order order) {
        orderService.create(order);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation("Find orders by user")
    public List<OrderDto> getOrdersByUserId(@PathVariable("id") long userId) {
        return orderDtoConverter.convertToDtos(orderService.getByUser(userService.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id = %d not found", userId)))));
    }
}
