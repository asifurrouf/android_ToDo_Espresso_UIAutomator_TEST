package com.example.todo.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todo.model.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class ToDoRoomDatabase extends RoomDatabase {

    public static volatile ToDoRoomDatabase INSTANCE;
    public abstract ToDoDao toDoDao();

    public static ToDoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoRoomDatabase.class) {
                if (INSTANCE == null) {
                    //create our db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class, "todo_database").addCallback(roomDatabaseCallBack)
                             .build();
                }
            }
        }
        return INSTANCE;

    }
    private static RoomDatabase.Callback roomDatabaseCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();

        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ToDoDao todoDao;

        public PopulateDbAsync(ToDoRoomDatabase db) {
            todoDao = db.toDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

             return null;
        }
    }

}
