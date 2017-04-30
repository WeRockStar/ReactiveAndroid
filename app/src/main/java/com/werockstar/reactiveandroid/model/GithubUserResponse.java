package com.werockstar.reactiveandroid.model;

import com.google.gson.annotations.SerializedName;

public class GithubUserResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("blog")
    private String blog;

    public GithubUserResponse(String name, String avatarUrl, String blog) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBlog() {
        return blog;
    }
}
