package com.harris.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TodoEditActivity extends Activity {
    private EditText editText;
    private Button saveButton;
    private String todoContent;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_edit);
        editText = (EditText) findViewById(R.id.editTodoText);
        saveButton = (Button) findViewById(R.id.btn_save);
        todoContent = getIntent().getStringExtra("content");
        position = getIntent().getIntExtra("position", -1);
        editText.setText(todoContent);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("position", position);
                data.putExtra("content", editText.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_edit, menu);
        return true;
    }
}
