package com.example.todo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class UserDao {
    
    /** ID */
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Integer userId;

    /** 名前 */
    @Column(name="user_name")
    private String userName;

    /** パスワード */
    @Column(nullable = false)
    private String password;

}
