package com.example.graphql.graphqlspqrexample.repository;

import com.example.graphql.graphqlspqrexample.connector.JsonPlaceholderConnector;
import com.example.graphql.graphqlspqrexample.connector.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PhotoRepository {
    private Map<Integer, Photo> photoStorage;

    public PhotoRepository(JsonPlaceholderConnector jsonPlaceholderConnector) {
        this.photoStorage = jsonPlaceholderConnector.getAllPhotos()
                .parallelStream()
                .collect(Collectors.toMap(Photo::getId, photo -> photo));
    }

    public List<Photo> getAllPhotos() {
        return new ArrayList<>(this.photoStorage.values());
    }

    public Optional<Photo> getPhotoById(int id) {
        if (this.photoStorage.containsKey(id)) {
            return Optional.of(this.photoStorage.get(id));
        }
        return Optional.empty();
    }

    public List<Photo> getPhotosOfAlbum(int albumId) {
        return this.photoStorage
                .entrySet()
                .parallelStream()
                .filter(photoEntry -> photoEntry.getValue().getAlbumId() == albumId)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
