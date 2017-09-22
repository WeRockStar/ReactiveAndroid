package com.werockstar.reactiveandroid.api


import com.werockstar.reactiveandroid.model.GithubUserResponse

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RxApi {

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Observable<GithubUserResponse>
}
