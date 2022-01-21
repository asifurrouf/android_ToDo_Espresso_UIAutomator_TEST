package com.example.todo.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo.data.ToDoDao;
import com.example.todo.data.ToDoRoomDatabase;
import com.example.todo.model.Todo;

import java.util.List;

public class ToDoRepository {
    private ToDoDao toDoDao;
    private LiveData<List<Todo>> allToDos;

    public ToDoRepository(Application application) {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);
        toDoDao = db.toDoDao();
        allToDos = toDoDao.getAllToDos();
    }

    public LiveData<List<Todo>> getAllToDos() {
        return allToDos;
    }

    public void insert(Todo todo) {
        new insertAsyncTask(toDoDao).execute(todo);
    }


    private class insertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private ToDoDao asyncTaskDao;
        public insertAsyncTask(ToDoDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Todo... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
