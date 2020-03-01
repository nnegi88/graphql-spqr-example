package com.example.graphql.graphqlspqrexample.repository;

import com.example.graphql.graphqlspqrexample.connector.JsonPlaceholderConnector;
import com.example.graphql.graphqlspqrexample.connector.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TodoRepository {
    private Map<Integer, Todo> todoStorage;

    public TodoRepository(JsonPlaceholderConnector jsonPlaceholderConnector) {
        this.todoStorage = jsonPlaceholderConnector.getAllTodos()
                .parallelStream()
                .collect(Collectors.toMap(Todo::getId, todo -> todo));
    }

    public List<Todo> getAllTodos() {
        return new ArrayList<>(this.todoStorage.values());
    }

    public Optional<Todo> getTodoById(int todoId) {
        return this.todoStorage
                .entrySet()
                .parallelStream()
                .filter(todoEntry -> todoEntry.getValue().getId() == todoId)
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public List<Todo> getTodosOfUser(int userId) {
        return this.todoStorage
                .entrySet()
                .parallelStream()
                .filter(todoEntry -> todoEntry.getValue().getUserId() == userId)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
