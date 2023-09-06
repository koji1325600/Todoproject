package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.dao.UserDao;
import com.example.todo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Boolean create(String userName, String password){
        if (userRepository.findByUserNameInt(userName) != null) {
            return false;
        }
        UserDao userDao = new UserDao();
        userDao.setUserName(userName);
        userDao.setPassword(new Pbkdf2PasswordEncoder().encode(password));

        userRepository.save(userDao);
        return true;
    }
    
}
