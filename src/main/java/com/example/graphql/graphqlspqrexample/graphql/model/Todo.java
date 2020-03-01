package com.example.graphql.graphqlspqrexample.graphql.model;

public class Todo {
    private int id;
    private String title;
    private boolean completed;

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static Todo of(com.example.graphql.graphqlspqrexample.connector.model.Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setId(todo.getId());
        newTodo.setTitle(todo.getTitle());
        newTodo.setCompleted(todo.getCompleted());
        return newTodo;
    }
}
