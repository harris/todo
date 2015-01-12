package com.harris.todo.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.harris.todo.R;

public class EditTodoFragment extends DialogFragment {

    private int position;

    public interface EditTodoDialogListener {
        void onFinishEditDialog(String content, int position);
    }

    private EditText editText;
    private Button editButton;

    public static EditTodoFragment newInstance(String content, int position) {
        EditTodoFragment frag = new EditTodoFragment();
        Bundle args = new Bundle();
        args.putString("content", content);
        args.putInt("position", position);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_todo, container);
        editText = (EditText) view.findViewById(R.id.txt_todo_content);
        editText.setText(getArguments().getString("content", "Enter here"));
        position = getArguments().getInt("position");

        editButton = (Button) view.findViewById(R.id.btn_edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTodoDialogListener activity = (EditTodoDialogListener)getActivity();
                activity.onFinishEditDialog(editText.getText().toString(), position);
                dismiss();
            }
        });
        getDialog().setTitle("Edit Todo");
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return view;
    }
}
