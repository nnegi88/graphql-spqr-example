package com.example.graphql.graphqlspqrexample.graphql.service;

import com.example.graphql.graphqlspqrexample.graphql.model.Album;
import com.example.graphql.graphqlspqrexample.graphql.model.Post;
import com.example.graphql.graphqlspqrexample.graphql.model.Todo;
import com.example.graphql.graphqlspqrexample.graphql.model.User;
import com.example.graphql.graphqlspqrexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserGraphQLService {
    private final UserRepository userRepository;
    private final PostGraphQLService postGraphQLService;
    private final TodoGraphQLService todoGraphQLService;
    private final AlbumGraphQLService albumGraphQLService;

    public UserGraphQLService(UserRepository userRepository, PostGraphQLService postGraphQLService, TodoGraphQLService todoGraphQLService, AlbumGraphQLService albumGraphQLService) {
        this.userRepository = userRepository;
        this.postGraphQLService = postGraphQLService;
        this.todoGraphQLService = todoGraphQLService;
        this.albumGraphQLService = albumGraphQLService;
    }

    public List<User> getAllUsers()  {
        List<com.example.graphql.graphqlspqrexample.connector.model.User> users = userRepository.getAllUsers();
        return users.parallelStream()
                .map(user -> {
                    List<Post> posts = postGraphQLService.getPostsOfUser(user.getId());
                    List<Todo> todos = todoGraphQLService.getTodosOfUser(user.getId());
                    List<Album> albums = albumGraphQLService.getAlbumsOfUser(user.getId());
                    return User.of(user, posts, todos, albums);
                }).collect(Collectors.toList());
    }

    public User getUserById(int id)  {
        Optional<com.example.graphql.graphqlspqrexample.connector.model.User> user = userRepository.getUserById(id);
        if (user.isPresent()) {
            List<Post> posts = postGraphQLService.getPostsOfUser(user.get().getId());
            List<Todo> todos = todoGraphQLService.getTodosOfUser(id);
            List<Album> albums = albumGraphQLService.getAlbumsOfUser(id);
            return User.of(user.get(), posts, todos, albums);
        }
        return null;
    }
}
