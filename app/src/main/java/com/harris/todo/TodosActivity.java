package com.harris.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.harris.todo.adapter.TodoAdapter;
import com.harris.todo.models.Todo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TodosActivity extends Activity {
    private static final int REQUEST_CODE = 20;
    private ListView lvItems;
    private List<Todo> todoItems;
    private TodoAdapter<Todo> todoAdapter;
    private EditText editText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        readItems();
        editText = (EditText) findViewById(R.id.editText);
        lvItems = (ListView) findViewById(R.id.lvitems);
        addButton = (Button) findViewById(R.id.btn_add);
        todoAdapter = new TodoAdapter<Todo>(getBaseContext(), android.R.layout.simple_list_item_1, todoItems);
        lvItems.setAdapter(todoAdapter);
        setupButtonListener();
        setupListViewListener();
    }

    private void setupButtonListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemText = editText.getText().toString();
                todoItems.add(new Todo(itemText));
                editText.setText("");
                writeItems();
            }
        });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<Todo>();
            for (String todoContent: FileUtils.readLines(todoFile)) {
                todoItems.add(new Todo(todoContent));
            }
        } catch (IOException e) {
            todoItems = new ArrayList<Todo>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                todoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), TodoEditActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("content", todoItems.get(position).getContent());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Todo todo = todoItems.get(data.getIntExtra("position", -1));
            todo.setContent(data.getStringExtra("content"));
            todoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds todoItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todos, menu);
        return true;
    }
}
