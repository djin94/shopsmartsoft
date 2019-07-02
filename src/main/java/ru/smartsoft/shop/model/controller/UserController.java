package ru.smartsoft.shop.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.service.UserService;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public ModelAndView showMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user = userService.getCurrentUser();
        if (user.isPresent()) {
            modelAndView.addObject("currentUser", user);
            modelAndView.setViewName("user/index");
        }
        else
        {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }
}
