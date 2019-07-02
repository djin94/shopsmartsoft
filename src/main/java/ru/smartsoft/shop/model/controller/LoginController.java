package ru.smartsoft.shop.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.smartsoft.shop.model.entity.User;
import ru.smartsoft.shop.model.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public String showUserRegistrationPage() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(ModelAndView modelAndView,
                                      @ModelAttribute("user") @Valid User user) {
        userService.create(user);
        return modelAndView;
    }
}
