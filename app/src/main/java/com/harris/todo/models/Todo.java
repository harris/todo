package com.harris.todo.models;

/**
 * Created by harris on 1/11/15.
 */
public class Todo {
    private String content;

    public Todo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
