package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.todo.dao.TodoDao;

public interface TodoRepository extends JpaRepository<TodoDao, String>, JpaSpecificationExecutor<TodoDao> {
    /** ユーザ名でTODOリスト取得 日付ソート 昇順*/
    @Query("SELECT X FROM TodoDao X WHERE X.name = ?1 ORDER BY X.date")
    List<TodoDao> findByuserNameDateSortAscList(String name);

    /** ユーザ名でTODOリスト取得 日付ソート 降順*/
    @Query("SELECT X FROM TodoDao X WHERE X.name = ?1 ORDER BY X.date DESC")
    List<TodoDao> findByuserNameDateSortDescList(String name);

    /** ユーザ名 タイトル近似値検索 TODOリスト取得 */
    @Query("SELECT X FROM TodoDao X WHERE X.name = ?1 AND X.title LIKE %?2% ORDER BY X.date DESC")
    List<TodoDao> findByuserNameTitleSeachDateSortAscList(String name, String title);
}
