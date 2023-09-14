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
import org.springframework.validation.annotation.Validated;

import com.example.todo.dao.TodoDao;
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

    /** TODO管理画面に遷移する */
    @GetMapping(path="todoList")
    String todo(Model model) {
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        List<TodoDao> todoList = todoRepository.findByuserNameDateSortAscList(userName);
        todoService.displayTodo(todoList, userName, model);
        return "todo/Todo";
    }

    /** 日付ソート */
    @GetMapping(path="dateSort")
    String dateSort(@RequestParam int dateSortId, Model model){
        //ソートチェック
        if (dateSortId == 1) {
            return "redirect:todoList";
        }
        todoService.dateSortTodo(model);
        return "todo/Todo";
    }

    /**　TODOを追加する */
    @PostMapping(path="addTodo")
    String addTodo(@Validated TodoForm todoForm, Model model){
        todoService.addTodo(todoForm);
        return "redirect:todoList";
    }

    /**　TODOを削除する */
    @PostMapping(path = "removeTodo")
    String removeTodo(@RequestParam String id, Model model){
        todoService.removeTodo(id);
        return "redirect:todoList";
    }

    /**　チェックを反映 */
    @PostMapping(path = "check")
    String check(@RequestParam String id, int dateSortId, Model model){
        TodoDao todoDao = todoRepository.findById(id).get();
        if (todoDao.getIsClose() == null) {
            todoDao.setIsClose(true);
        } else {
            todoDao.setIsClose(null);
        }
        todoService.updateTodo(id, todoDao);

        if (dateSortId == 0) {
            return "redirect:todoList";
        }
        todoService.dateSortTodo(model);
        return "todo/Todo";
    }

    /**　TODO編集画面遷移 */
    @PostMapping(path = "editTodo")
    String editTodo(@RequestParam String id, Model model){
        TodoDao todoDao = todoRepository.findById(id).get();
        String check = "完了";

        if (todoDao.getIsClose() == null) {
            check = "未完了";
        }

        model.addAttribute("todo", todoDao);
        model.addAttribute("check", check);
        return "todo/Edit";
    }

    /**　TODO編集 */
    @PostMapping(path = "edit")
    String edit(@Validated TodoForm todoForm, Model model){
        TodoDao todoDao = todoRepository.findById(todoForm.getId()).get();
        
        todoDao.setDate(todoForm.getDate());
        todoDao.setTitle(todoForm.getTitle());
        todoDao.setBody(todoForm.getBody());
        todoDao.setIsRelease(todoForm.getIsRelease());
        todoService.updateTodo(todoForm.getId(), todoDao);
        return "redirect:todoList";
    }

    /** TODO検索 */
    @GetMapping(path = "seachTodo")
    String seachTodo(@RequestParam String seach, Model model){
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        List<TodoDao> todoList = todoRepository.findByuserNameTitleSeachDateSortAscList(userName, seach);
        todoService.displayTodo(todoList, userName, model);
        model.addAttribute("seach", seach);
        return "todo/Todo";
    }

    /** 公開TODO検索 */
    @GetMapping(path = "seachReleaseTodo")
    String seachReleaseTodo(@RequestParam String seach, Model model){
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        List<TodoDao> todoList = todoRepository.findByReleaseTitleSeachDateSortAscList(seach);
        todoService.displayTodo(todoList, userName, model);
        return "todo/ReleaseTodo";
    }

    /** 公開TODO画面遷移 */
    @GetMapping(path = "releaseTodo")
    String releaseTodo(Model model){
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        List<TodoDao> todoList = todoRepository.findByReleaseDateSortAscList();
        todoService.displayTodo(todoList, userName, model);
        return "todo/ReleaseTodo";
    }

    /** いいね */
    @PostMapping(path = "good")
    String goodTodo(@RequestParam String id, Model model){
        TodoDao todoDao = todoRepository.findById(id).get();
        todoDao.setGood(todoDao.getGood() + 1);
        todoService.updateTodo(id, todoDao);
        return "redirect:releaseTodo";
    }
}
