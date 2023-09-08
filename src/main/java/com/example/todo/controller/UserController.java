package com.example.todo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.dao.UserDao;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    String user() {
        return "users/CreateUser";
    }

    @PostMapping(path = "create")
    String create(@RequestParam String userName, String mailaddress, String password) {
        Boolean isCreate = userService.create(userName, mailaddress, password);
        if (isCreate) {
            return "redirect:/login";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping(path = "profile")
    String profile(Model model) {
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        UserDao userDao = userRepository.findByUserNameDao(userName);
        model.addAttribute("user", userDao);
        return "users/Profile";
    }

}
