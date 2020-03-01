package com.example.graphql.graphqlspqrexample.graphql.model;

public class Photo {
    private Integer id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public static Photo of(com.example.graphql.graphqlspqrexample.connector.model.Photo photo) {
        Photo newPhoto = new Photo();
        newPhoto.setId(photo.getId());
        newPhoto.setTitle(photo.getTitle());
        newPhoto.setUrl(photo.getUrl());
        newPhoto.setThumbnailUrl(photo.getThumbnailUrl());
        return newPhoto;
    }
}
