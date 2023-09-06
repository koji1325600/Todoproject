package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    String user() {
      return "users/CreateUser";
    }

    @PostMapping(path="create")
    String create(@RequestParam String userName, String password) {
        Boolean isCreate = userService.create(userName, password);
        if (isCreate) {
          return "redirect:/login";
        } else {
          return user();
        }
    }

}
