package com.example.graphql.graphqlspqrexample.graphql.service;

import com.example.graphql.graphqlspqrexample.graphql.model.Album;
import com.example.graphql.graphqlspqrexample.graphql.model.Photo;
import com.example.graphql.graphqlspqrexample.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumGraphQLService {
    private final AlbumRepository albumRepository;
    private final PhotoGraphQLService photoGraphQLService;

    public AlbumGraphQLService(AlbumRepository albumRepository, PhotoGraphQLService photoGraphQLService) {
        this.albumRepository = albumRepository;
        this.photoGraphQLService = photoGraphQLService;
    }

    public List<Album> getAllAlbums()  {
        List<com.example.graphql.graphqlspqrexample.connector.model.Album> albums = albumRepository.getAllAlbums();
        return albums.parallelStream()
                .map(album -> {
                    List<Photo> photos = photoGraphQLService.getPhotosOfAlbum(album.getId());
                    return Album.of(album, photos);
                }).collect(Collectors.toList());
    }

    public List<Album> getAlbumsOfUser(int userId)  {
        List<com.example.graphql.graphqlspqrexample.connector.model.Album> albums = albumRepository.getAlbumsOfUser(userId);
        return albums.parallelStream()
                .map(album -> {
                    List<Photo> photos = photoGraphQLService.getPhotosOfAlbum(album.getId());
                    return Album.of(album, photos);
                }).collect(Collectors.toList());
    }
}
