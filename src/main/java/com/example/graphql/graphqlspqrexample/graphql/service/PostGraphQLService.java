package com.example.graphql.graphqlspqrexample.graphql.service;

import com.example.graphql.graphqlspqrexample.graphql.model.Comment;
import com.example.graphql.graphqlspqrexample.graphql.model.Post;
import com.example.graphql.graphqlspqrexample.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostGraphQLService {
    private final PostRepository postRepository;
    private final CommentGraphQLService commentGraphQLService;

    public PostGraphQLService(PostRepository postRepository, CommentGraphQLService commentGraphQLService) {
        this.postRepository = postRepository;
        this.commentGraphQLService = commentGraphQLService;
    }

    public List<Post> getAllPosts() {
        List<com.example.graphql.graphqlspqrexample.connector.model.Post> posts = postRepository.getAllPosts();
        return posts.parallelStream()
                .map(post -> {
                    List<Comment> comments = commentGraphQLService.getCommentsOfPost(post.getId());
                    return Post.of(post, comments);
                }).collect(Collectors.toList());
    }

    public Post getPostById(int id) {
        Optional<com.example.graphql.graphqlspqrexample.connector.model.Post> postById = postRepository.getPostById(id);
        if (postById.isPresent()) {
            List<Comment> comments = commentGraphQLService.getCommentsOfPost(id);
            return Post.of(postById.get(), comments);
        }
        return null;
    }

    public List<Post> getPostsOfUser(int userId) {
        List<com.example.graphql.graphqlspqrexample.connector.model.Post> posts = postRepository.getPostsOfUser(userId);
        return posts.parallelStream()
                .map(post -> {
                    List<Comment> comments = commentGraphQLService.getCommentsOfPost(post.getId());
                    return Post.of(post, comments);
                }).collect(Collectors.toList());
    }
}
