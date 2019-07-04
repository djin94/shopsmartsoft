package ru.smartsoft.shop.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.smartsoft.shop.model.dto.BuyItemDto;
import ru.smartsoft.shop.model.dto.converter.BuyItemDtoConverter;
import ru.smartsoft.shop.model.dto.converter.OrderDtoConverter;
import ru.smartsoft.shop.model.dto.converter.impl.BuyItemDtoConverterImpl;
import ru.smartsoft.shop.model.dto.converter.impl.OrderDtoConverterImpl;
import ru.smartsoft.shop.model.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = AdminController.URL)
public class AdminController {

    public static final String URL = "/admin";

    private BuyItemDtoConverter buyItemDtoConverter = new BuyItemDtoConverterImpl();

    @Autowired
    private OrderService orderService;

    private OrderDtoConverter orderDtoConverter = new OrderDtoConverterImpl();

    @GetMapping
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @GetMapping(value = "/orders-last-week")
    public ModelAndView getOrdersForLastWeek() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/orders-last-week");
        List<BuyItemDto> buyItems = orderService.getOrdersForLastWeek().stream()
                .collect(ArrayList::new, (buyItemDtos, order) -> buyItemDtos.addAll(buyItemDtoConverter.convertToBuyItemDtos(order.getBuyItems())), ArrayList::addAll);
        modelAndView.addObject("buyItems", buyItems);
        return modelAndView;
    }

    @GetMapping(value = "/most-sold-item")
    public ModelAndView getMostSoldItem() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/most-sold-item");
        List<BuyItemDto> buyItems = orderService.getOrdersForLastWeek().stream()
                .collect(ArrayList::new, (buyItemDtos, order) -> buyItemDtos.addAll(buyItemDtoConverter.convertToBuyItemDtos(order.getBuyItems())), ArrayList::addAll);
        modelAndView.addObject("buyItem", buyItems);
        return modelAndView;
    }
}
