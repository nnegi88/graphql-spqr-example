package com.example.graphql.graphqlspqrexample.graphql.service;

import com.example.graphql.graphqlspqrexample.graphql.model.Todo;
import com.example.graphql.graphqlspqrexample.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoGraphQLService {
    private final TodoRepository todoRepository;

    public TodoGraphQLService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos()
                .parallelStream()
                .map(Todo::of)
                .collect(Collectors.toList());
    }

    public Todo getTodoById(int id) {
        return todoRepository.getTodoById(id)
                .map(Todo::of)
                .orElse(null);
    }

    public List<Todo> getTodosOfUser(int userId) {
        return todoRepository.getTodosOfUser(userId)
                .parallelStream()
                .map(Todo::of)
                .collect(Collectors.toList());
    }
}
