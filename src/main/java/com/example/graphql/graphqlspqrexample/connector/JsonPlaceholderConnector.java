package com.example.graphql.graphqlspqrexample.connector;

import com.example.graphql.graphqlspqrexample.connector.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JsonPlaceholderConnector {
    private final RestTemplate restTemplate;
    private final String jsonPlaceholderUrl;

    public JsonPlaceholderConnector(RestTemplate restTemplate, @Value("${service.json-placeholder.url}") String jsonPlaceholderUrl) {
        this.restTemplate = restTemplate;
        this.jsonPlaceholderUrl = jsonPlaceholderUrl;
    }

    public List<Post> getAllPosts() {
        ResponseEntity<Post[]> response = restTemplate.getForEntity(jsonPlaceholderUrl.concat("/posts"), Post[].class);
        Post[] posts = response.getBody();
        if (posts==null) {
            return Collections.emptyList();
        }
        return Arrays.asList(posts);
    }

    public List<Comment> getAllComments() {
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(jsonPlaceholderUrl.concat("/comments"), Comment[].class);
        Comment[] comments = response.getBody();
        if (comments==null) {
            return Collections.emptyList();
        }
        return Arrays.asList(comments);
    }

    public List<Album> getAllAlbums() {
        ResponseEntity<Album[]> response = restTemplate.getForEntity(jsonPlaceholderUrl.concat("/albums"), Album[].class);
        Album[] albums = response.getBody();
        if (albums==null) {
            return Collections.emptyList();
        }
        return Arrays.asList(albums);
    }

    public List<Photo> getAllPhotos() {
        ResponseEntity<Photo[]> response = restTemplate.getForEntity(jsonPlaceholderUrl.concat("/photos"), Photo[].class);
        Photo[] photos = response.getBody();
        if (photos==null) {
            return Collections.emptyList();
        }
        return Arrays.asList(photos);
    }

    public List<Todo> getAllTodos() {
        ResponseEntity<Todo[]> response = restTemplate.getForEntity(jsonPlaceholderUrl.concat("/todos"), Todo[].class);
        Todo[] todos = response.getBody();
        if (todos==null) {
            return Collections.emptyList();
        }
        return Arrays.asList(todos);
    }

    public List<User> getAllUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(jsonPlaceholderUrl.concat("/users"), User[].class);
        User[] users = response.getBody();
        if (users==null) {
            return Collections.emptyList();
        }
        return Arrays.asList(users);
    }
}
