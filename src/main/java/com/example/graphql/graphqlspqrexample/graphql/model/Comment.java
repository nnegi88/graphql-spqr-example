package com.example.graphql.graphqlspqrexample.graphql.model;

public class Comment {
    private Integer id;
    private String name;
    private String email;
    private String body;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Comment of(com.example.graphql.graphqlspqrexample.connector.model.Comment comment) {
        Comment newComment = new Comment();
        newComment.setId(comment.getId());
        newComment.setName(comment.getName());
        newComment.setEmail(comment.getEmail());
        newComment.setBody(comment.getBody());
        return newComment;
    }
}
