package com.example.graphql.graphqlspqrexample.repository;

import com.example.graphql.graphqlspqrexample.connector.JsonPlaceholderConnector;
import com.example.graphql.graphqlspqrexample.connector.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AlbumRepository {
    private Map<Integer, Album> albumStorage;

    public AlbumRepository(JsonPlaceholderConnector jsonPlaceholderConnector) {
        this.albumStorage = jsonPlaceholderConnector.getAllAlbums()
                .parallelStream()
                .collect(Collectors.toMap(Album::getId, album -> album));
    }

    public List<Album> getAllAlbums() {
        return new ArrayList<>(this.albumStorage.values());
    }

    public Optional<Album> getAlbumById(int id) {
        if (this.albumStorage.containsKey(id)) {
            return Optional.of(this.albumStorage.get(id));
        }
        return Optional.empty();
    }

    public List<Album> getAlbumsOfUser(int userId) {
        return this.albumStorage
                .entrySet()
                .parallelStream()
                .filter(albumEntry -> albumEntry.getValue().getUserId() == userId)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
