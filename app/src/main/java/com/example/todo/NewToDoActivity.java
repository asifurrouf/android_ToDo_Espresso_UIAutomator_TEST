package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewToDoActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.todo";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        editText = findViewById(R.id.edit_todo);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(TextUtils.isEmpty(editText.getText())) {
                    setResult(RESULT_CANCELED, intent);
                } else {
                    String todoString = editText.getText().toString();
                    intent.putExtra(EXTRA_REPLY, todoString);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}