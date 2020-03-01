package com.example.graphql.graphqlspqrexample.graphql.service;


import com.example.graphql.graphqlspqrexample.graphql.model.Photo;
import com.example.graphql.graphqlspqrexample.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoGraphQLService {
    private final PhotoRepository photoRepository;

    public PhotoGraphQLService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhotos()  {
        return photoRepository.getAllPhotos()
                .parallelStream()
                .map(Photo::of)
                .collect(Collectors.toList());

    }

    public List<Photo> getPhotosOfAlbum(int albumId)  {
        return photoRepository.getPhotosOfAlbum(albumId)
                .parallelStream()
                .map(Photo::of)
                .collect(Collectors.toList());
    }
}
