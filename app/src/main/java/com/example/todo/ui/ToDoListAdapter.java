package com.example.todo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.Todo;

import java.text.BreakIterator;
import java.util.List;
import java.util.zip.Inflater;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.TodoViewHolder> {

    private final LayoutInflater toDoInflater;
    private List<Todo> todoList;

    public ToDoListAdapter(Context context) {
        toDoInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = toDoInflater.inflate(R.layout.recyclerview_item, viewGroup, false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if(todoList != null) {
            Todo current = todoList.get(position);
            holder.toDoTextView.setText(current.getToDo());
        } else {
            holder.toDoTextView.setText("No no todo");
        }

    }
    public void setTodo(List<Todo> toDos) {
        todoList = toDos;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if(todoList != null) {
            return todoList.size();

        } else {
            return 0;
        }

    }

    public class TodoViewHolder extends RecyclerView.ViewHolder{

        public TextView toDoTextView;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoTextView = itemView.findViewById(R.id.textView);
        }
    }
}
