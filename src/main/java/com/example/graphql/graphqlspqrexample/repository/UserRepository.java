package com.example.graphql.graphqlspqrexample.repository;

import com.example.graphql.graphqlspqrexample.connector.JsonPlaceholderConnector;
import com.example.graphql.graphqlspqrexample.connector.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private Map<Integer, User> userStorage;

    public UserRepository(JsonPlaceholderConnector jsonPlaceholderConnector) {
        this.userStorage = jsonPlaceholderConnector.getAllUsers()
                .parallelStream()
                .collect(Collectors.toMap(User::getId, user -> user));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(this.userStorage.values());
    }

    public Optional<User> getUserById(int id) {
        if (this.userStorage.containsKey(id)) {
            return Optional.of(this.userStorage.get(id));
        }
        return Optional.empty();
    }
}
