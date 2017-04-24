package com.werockstar.reactiveandroid.api;


import com.werockstar.reactiveandroid.model.GithubUserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RxApi {

    @GET("users/{user}")
    Observable<GithubUserResponse> getUser(@Path("user") String user);
}
