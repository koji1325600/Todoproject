package com.example.todo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.dao.UserDao;
import com.example.todo.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
    HttpServletRequest httpServletRequest;

	@Autowired
    UserRepository userRepository;
    
	@GetMapping(path="login")
	String login(Model model) {
		return "/Login";
	}

	    /** ログイン処理を行う */
    @PostMapping(path="todoLogin")
    String todoLogin(@RequestParam String mailaddress, String password, Pbkdf2PasswordEncoder passwordEncoder, Model model){
        UserDao userDao = userRepository.findByMailaddressDao(mailaddress);
        if (userDao == null) {
            //ログイン画面に戻る
            return "redirect:/login";
        }
        //パスワードがDBと一致しなかった場合
        if (!passwordEncoder.matches(password,userDao.getPassword())) {
            //ログイン画面に戻る
            return "redirect:/login";
        }
        //Sessionにユーザ名を設定
        httpServletRequest.getSession().setAttribute("userName", userDao.getUserName());
        httpServletRequest.getSession().setAttribute("mailaddress", userDao.getMailaddress());
        return "redirect:todo/todoList";
    }

	@GetMapping(path = "logout")
	String logout(HttpServletRequest httpServletRequest){
		httpServletRequest.getSession().invalidate();
		return "/Login";
	}
}
