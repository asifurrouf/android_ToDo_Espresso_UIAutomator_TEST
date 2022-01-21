package com.example.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todo.model.Todo;

import java.util.List;

@Dao
public interface ToDoDao {
    // CRUD
    @Insert
    void insert(Todo toDo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("DELETE FROM TODO_TABLE WHERE id = :id")
    int deleteAToDo(int id);

    @Query("UPDATE TODO_TABLE SET `todo_col` = :todoText WHERE id = :id")
    int updatetoDoItem(int id, String todoText);

    @Query("SELECT * FROM todo_table ORDER BY `todo_col` DESC")
    LiveData<List<Todo>> getAllToDos();

}
