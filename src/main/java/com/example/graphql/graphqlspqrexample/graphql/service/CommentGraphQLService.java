package com.example.graphql.graphqlspqrexample.graphql.service;

import com.example.graphql.graphqlspqrexample.graphql.model.Comment;
import com.example.graphql.graphqlspqrexample.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentGraphQLService {
    private final CommentRepository commentRepository;

    public CommentGraphQLService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.getAllComments()
                .parallelStream()
                .map(Comment::of)
                .collect(Collectors.toList());
    }

    public Comment getCommentById(int id) {
        return commentRepository.getCommentById(id)
                .map(Comment::of)
                .orElse(null);
    }

    public List<Comment> getCommentsOfPost(int postId) {
        return commentRepository.getCommentsOfPost(postId)
                .parallelStream()
                .map(Comment::of)
                .collect(Collectors.toList());
    }
}
