package com.example.graphql.graphqlspqrexample.graphql.model;

import java.util.List;

public class Post {
    private Integer id;
    private String title;
    private String body;
    private List<Comment> comments;

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public static Post of(com.example.graphql.graphqlspqrexample.connector.model.Post post, List<Comment> comments) {
        Post newPost = new Post();
        newPost.setId(post.getId());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setComments(comments);
        return newPost;
    }
}
