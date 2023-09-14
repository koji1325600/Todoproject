package com.example.todo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.todo.dao.TodoDao;
import com.example.todo.form.TodoForm;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;

@Service
public class TodoService {
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    /** TODO一覧表示処理 */
    public void displayTodo(List<TodoDao> todoList, String userName, Model model){
        if (todoList.size() == 0) {
            model.addAttribute("todoError", "まだ登録されていません!!");
        }
        model.addAttribute("dateSortId", 0);
        model.addAttribute("userName", userName);
        model.addAttribute("todoList", todoList);
    }
    
    /** TODO追加処理 */
    public void addTodo(TodoForm todoForm){
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        todoForm.setName(userName);

        TodoDao todoDao = new TodoDao();
        BeanUtils.copyProperties(todoForm, todoDao);
        todoDao.addTodoDao();
        todoRepository.save(todoDao);
    }

    /** TODO削除処理 */
    public void removeTodo(String id){
        todoRepository.deleteById(id);
    }

    /** TODO更新処理 */
    public void updateTodo(String id, TodoDao todoDao){
        removeTodo(id);
        todoRepository.save(todoDao);
    }

    /** TODO日付降順処理 */
    public void dateSortTodo(Model model){
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        List<TodoDao> todoList = todoRepository.findByuserNameDateSortDescList(userName);

        if (todoList.size() == 0) {
            model.addAttribute("todoError", "まだ登録されていません!!");
        }
        model.addAttribute("dateSortId", 1);
        model.addAttribute("userName", userName);
        model.addAttribute("todoList", todoList);
    }

}
