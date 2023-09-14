package com.example.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.dao.TodoDao;
import com.example.todo.dao.UserDao;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoService;
import com.example.todo.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserService userService;

    @Autowired
    TodoService todoService;

    /** ユーザ作成画面遷移 */
    @GetMapping
    String user() {
        return "users/CreateUser";
    }

    /** ユーザ作成 */
    @PostMapping(path = "create")
    String create(@RequestParam String userName, String mailaddress, String password) {
        Boolean isCreate = userService.create(userName, mailaddress, password);
        if (isCreate) {
            return "redirect:/login";
        } else {
            return "redirect:/users";
        }
    }

    /** ユーザプロフィール画面遷移 */
    @GetMapping(path = "profile")
    String profile(Model model) {
        int userId = Integer.parseInt(httpServletRequest.getSession().getAttribute("userId").toString());
        String userName = userRepository.findById(userId).get().getUserName();
        UserDao userDao = userRepository.findByUserNameDao(userName);
        model.addAttribute("user", userDao);
        return "users/Profile";
    }

    /** ユーザ編集 */
    @PostMapping(path = "edit")
    String edit(@RequestParam String userName, String mailaddress, Model model) {
        int userId = Integer.parseInt(httpServletRequest.getSession().getAttribute("userId").toString());
        String userMail = userRepository.findById(userId).get().getMailaddress();
        UserDao userDao = userRepository.findByMailaddressDao(userMail);
        List<TodoDao> todoList = todoRepository.findByuserIdDateSortAscList(userDao.getUserId());
        
        for (TodoDao todoDao: todoList) {
            todoDao.setName(userName);
            todoService.updateTodo(todoDao.getId(), todoDao);
        }

        userDao.setUserName(userName);
        userDao.setMailaddress(mailaddress);
        userService.updateUser(userDao.getUserId(), userDao);
        return "redirect:/login";
    }

}
