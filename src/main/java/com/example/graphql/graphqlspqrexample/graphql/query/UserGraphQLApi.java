package com.example.graphql.graphqlspqrexample.graphql.query;

import com.example.graphql.graphqlspqrexample.graphql.service.UserGraphQLService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Component;

import java.util.List;

@GraphQLApi
@Component
public class UserGraphQLApi {
    private final UserGraphQLService userGraphQLService;

    public UserGraphQLApi(UserGraphQLService userGraphQLService) {
        this.userGraphQLService = userGraphQLService;
    }

    @GraphQLQuery
    public List<com.example.graphql.graphqlspqrexample.graphql.model.User> users() {
        return userGraphQLService.getAllUsers();
    }

    @GraphQLQuery
    public com.example.graphql.graphqlspqrexample.graphql.model.User user(int id) {
        return userGraphQLService.getUserById(id);
    }
}
