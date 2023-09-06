package com.example.todo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    /** TODO追加処理 */
    public void addTodo(TodoForm todoForm){
        String userName = httpServletRequest.getSession().getAttribute("userName").toString();
        todoForm.setName(userName);

        TodoDao todoDao = new TodoDao();
        BeanUtils.copyProperties(todoForm, todoDao);
        todoDao.addTodoDao();
        todoRepository.save(todoDao);
    }

    public void removeTodo(String id){
        todoRepository.deleteById(id);
    }

    public void updateTodo(String id, TodoDao todoDao){
        removeTodo(id);
        todoRepository.save(todoDao);
    }
}
