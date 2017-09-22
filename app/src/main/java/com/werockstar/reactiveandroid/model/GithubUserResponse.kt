package com.werockstar.reactiveandroid.model

import com.google.gson.annotations.SerializedName

class GithubUserResponse(@SerializedName("name")
                         val name: String, @SerializedName("avatar_url")
                         val avatarUrl: String, @SerializedName("blog")
                         val blog: String)
