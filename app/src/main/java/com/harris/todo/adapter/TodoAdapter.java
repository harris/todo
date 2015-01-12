package com.harris.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.harris.todo.R;
import com.harris.todo.models.Todo;

import java.util.List;

/**
 * Created by harris on 1/11/15.
 */
public class TodoAdapter<T> extends ArrayAdapter<Todo> {
    public TodoAdapter(Context context, int resource, List<Todo> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Todo todo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }

        TextView todoContent = (TextView) convertView.findViewById(R.id.todo_content);
        todoContent.setText(todo.getContent());

        return convertView;
    }
}
