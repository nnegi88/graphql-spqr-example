package com.example.graphql.graphqlspqrexample.graphql.model;

import java.util.List;

public class Album {
    private Integer id;
    private String title;
    private List<Photo> photos;

    public Album() {
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public static Album of(com.example.graphql.graphqlspqrexample.connector.model.Album album, List<Photo> photos) {
        Album newAlbum = new Album();
        newAlbum.setId(album.getId());
        newAlbum.setTitle(album.getTitle());
        newAlbum.setPhotos(photos);
        return newAlbum;
    }
}
