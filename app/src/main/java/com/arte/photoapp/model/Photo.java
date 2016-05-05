package com.arte.photoapp.model;


import java.io.Serializable;
import java.util.regex.Pattern;

public class Photo {

    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        String bg = url.substring(url.length() - 6, url.length());
        return "https://placeholdit.imgix.net/~text?txtsize=20&bg=" + bg + "&txt=150%C3%97150&w=500&h=500";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        // http://placehold.it/150/30ac17
        // https://placeholdit.imgix.net/~text?txtsize=14&bg=30ac17&txt=150%C3%97150&w=150&h=150
        String bg = thumbnailUrl.substring(thumbnailUrl.length() - 6, thumbnailUrl.length());
        return "https://placeholdit.imgix.net/~text?txtsize=14&bg=" + bg + "&txt=150%C3%97150&w=150&h=150";
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
