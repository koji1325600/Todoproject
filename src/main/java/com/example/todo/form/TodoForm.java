package com.example.todo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class TodoForm {
    /** ID */
    private String id;

    /** ユーザID */
    private int userId;

    /** 登録者 */
    private String name;

    /** 件名 */
    private String title;

    /** 内容 */
    private String body;
    
    /** 日付*/
    private Date date;
    
    /** 状況*/
    private Boolean isClose;

    /** 公開設定 */
    private Boolean isRelease;
}
