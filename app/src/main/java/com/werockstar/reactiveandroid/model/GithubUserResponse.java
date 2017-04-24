package com.werockstar.reactiveandroid.model;

import com.google.gson.annotations.SerializedName;

public class GithubUserResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("bLog")
    private String blog;

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
