package com.example.graphql.graphqlspqrexample.repository;

import com.example.graphql.graphqlspqrexample.connector.JsonPlaceholderConnector;
import com.example.graphql.graphqlspqrexample.connector.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private Map<Integer, Post> postStorage;

    public PostRepository(JsonPlaceholderConnector jsonPlaceholderConnector) {
        this.postStorage = jsonPlaceholderConnector.getAllPosts()
                .parallelStream()
                .collect(Collectors.toMap(Post::getId, post -> post));
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(this.postStorage.values());
    }

    public Optional<Post> getPostById(int postId) {
        return this.postStorage
                .entrySet()
                .parallelStream()
                .filter(postMap -> postMap.getValue().getId() == postId)
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public List<Post> getPostsOfUser(int userId) {
        return this.postStorage
                .entrySet()
                .parallelStream()
                .filter(postMap -> postMap.getValue().getUserId() == userId)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
