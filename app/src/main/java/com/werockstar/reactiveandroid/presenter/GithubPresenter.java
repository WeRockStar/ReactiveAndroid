package com.werockstar.reactiveandroid.presenter;


import com.werockstar.reactiveandroid.api.RxApi;

import javax.inject.Inject;

public class GithubPresenter {


    private GithubPresenter.View view;
    private RxApi api;

    @Inject
    public GithubPresenter(RxApi api) {
        this.api = api;
    }

    public interface View {

    }

    public void attachView(GithubPresenter.View view) {
        this.view = view;
    }
}
