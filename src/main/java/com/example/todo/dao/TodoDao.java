package com.example.todo.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "todo")
public class TodoDao implements Serializable {
    
    /** デフォルトシリアルUID */
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    private String id;

    /** 登録者 */
    @Column
    private String name;

    /** 件名 */
    @Column
    private String title;

    /** 内容 */
    @Column
    private String body;
    
    /** 日付*/
    @Column
    private Date date;
    
    /** 状況*/
    @Column(name="is_close")
    private Boolean isClose;

    /** 公開設定*/
    @Column(name="is_release")
    private Boolean isRelease;

    /** いいね */
    @Column
    private int good;

    /**
     * デフォルトコンストラクタ。
     */
    public void addTodoDao() {
        this.id = UUID.randomUUID().toString();
    }

}
