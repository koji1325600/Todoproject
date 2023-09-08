package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.todo.dao.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Integer>, JpaSpecificationExecutor<UserDao> {
    @Query("SELECT X FROM UserDao X WHERE X.userName = ?1")
    UserDao findByUserNameDao(String userName);

    @Query("SELECT X FROM UserDao X WHERE X.mailaddress = ?1")
    UserDao findByMailaddressDao(String mailaddress);

    @Query("SELECT X.userId FROM UserDao X WHERE X.mailaddress = ?1")
    Integer findByMailaddressInt(String mailaddress);
}
