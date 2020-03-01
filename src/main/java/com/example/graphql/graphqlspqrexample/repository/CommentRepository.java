package com.example.graphql.graphqlspqrexample.repository;

import com.example.graphql.graphqlspqrexample.connector.JsonPlaceholderConnector;
import com.example.graphql.graphqlspqrexample.connector.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private Map<Integer, Comment> commentStorage;

    public CommentRepository(JsonPlaceholderConnector jsonPlaceholderConnector) {
        this.commentStorage = jsonPlaceholderConnector.getAllComments()
                .parallelStream()
                .collect(Collectors.toMap(Comment::getId, comment -> comment));
    }

    public List<Comment> getAllComments() {
        return new ArrayList<>(this.commentStorage.values());
    }

    public Optional<Comment> getCommentById(Integer commentId) {
        if (this.commentStorage.containsKey(commentId)) {
            return Optional.of(this.commentStorage.get(commentId));
        }
        return Optional.empty();
    }

    public List<Comment> getCommentsOfPost(int postId) {
        return this.commentStorage
                .entrySet()
                .parallelStream()
                .filter(commentEntry -> commentEntry.getValue().getPostId() == postId)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
