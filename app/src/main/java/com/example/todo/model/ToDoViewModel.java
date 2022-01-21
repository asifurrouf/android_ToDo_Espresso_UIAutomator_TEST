package com.example.todo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo.util.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {
    private ToDoRepository toDoRepository;
    private LiveData<List<Todo>> allToDos;


    public ToDoViewModel(@NonNull Application application) {

        super(application);
        toDoRepository = new ToDoRepository(application);
        allToDos = toDoRepository.getAllToDos();

    }

    public LiveData<List<Todo>> getAllToDos() {
        return allToDos;
    }

    public void insert(Todo toDo) {
        toDoRepository.insert(toDo);
    }

}
