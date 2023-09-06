package com.example.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.annotation.Validated;

import com.example.todo.dao.TodoDao;
import com.example.todo.dao.UserDao;
import com.example.todo.form.TodoForm;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoService todoService;

    /** ログイン処理を行う */
    @PostMapping(path="todoLogin")
    String todoLogin(@RequestParam String userName, String password, Pbkdf2PasswordEncoder passwordEncoder, Model model){
        UserDao userDao = userRepository.findByUserNameDao(userName);
        //パスワードがDBと一致しなかった場合
        if (!passwordEncoder.matches(password,userDao.getPassword())) {
            //ログイン画面に戻る
            return "redirect:/login";
        }
        //Sessionにユーザ名を設定
        httpServletRequest.getSession().setAttribute("userName", userName);
        return todo(model);
    }

    /** TODO管理画面に遷移する */
    @PostMapping(path="todoList")
    String todo(Model model) {
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        List<TodoDao> todoList = todoRepository.findByuserNameList(userName);

        model.addAttribute("userName", userName);
        model.addAttribute("todoList", todoList);
        return "todo/Todo";
    }

    /**　TODOを追加する */
    @PostMapping(path="addTodo")
    String addTodo(@Validated TodoForm todoForm, Model model){
        todoService.addTodo(todoForm);
        return todo(model);
    }

    /**　TODOを削除する */
    @PostMapping(path = "removeTodo")
    String removeTodo(@RequestParam String id, Model model){
        todoService.removeTodo(id);
        return todo(model);
    }

    /**　チェックを反映 */
    @PostMapping(path = "check")
    String check(@RequestParam String id, Model model){
        TodoDao todoDao = todoRepository.findById(id).get();
        if (todoDao.getIsClose() == null)
            todoDao.setIsClose(true);
        else {
            todoDao.setIsClose(null);
        }
        todoService.updateTodo(id, todoDao);
        return todo(model);
    }
}
