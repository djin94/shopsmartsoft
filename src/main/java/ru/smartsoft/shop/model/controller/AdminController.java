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
import ru.smartsoft.shop.model.service.BuyItemService;
import ru.smartsoft.shop.model.service.OrderService;
import ru.smartsoft.shop.model.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = AdminController.URL)
public class AdminController {

    public static final String URL = "/admin";

    private BuyItemDtoConverter buyItemDtoConverter = new BuyItemDtoConverterImpl();

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyItemService buyItemService;

    @Autowired
    private UserService userService;

    private OrderDtoConverter orderDtoConverter = new OrderDtoConverterImpl();

    @GetMapping
    public String getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        return "/admin/index";
    }

    @GetMapping(value = "/buying-items-last-week")
    public ModelAndView getOrdersForLastWeekPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/buying-items-last-week");
        List<BuyItemDto> buyItems = buyItemDtoConverter.convertToBuyItemDtos(buyItemService.getBuyItemsForLastWeek());
        modelAndView.addObject("buyItems", buyItems);
        return modelAndView;
    }

    @GetMapping(value = "/best-selling-item")
    public ModelAndView getMostSoldItemPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/best-selling-item");
        modelAndView.addObject("buyItem", buyItemService.getBestSellingItemForLastMonth());
        return modelAndView;
    }

    @GetMapping(value = "/best-buying-person-last-half-year")
    public ModelAndView getBestBuyingPersonForLastHalfYear() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/best-buying-person-last-half-year");
        modelAndView.addObject("user", userService.getBestBuyingPersonForLastHalfYear());
        return modelAndView;
    }

    @GetMapping(value = "/best-selling-items-by-18-years-old-person")
    public ModelAndView getBestSellingItemsBy18YearOldPerson() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/best-selling-items-by-18-years-old-person");
        modelAndView.addObject("buyItems", buyItemDtoConverter.convertToBuyItemDtos(buyItemService.getBestSellingItemsBy18YearsOldPerson()));
        return modelAndView;
    }
}
