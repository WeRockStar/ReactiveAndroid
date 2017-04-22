package com.werockstar.reactiveandroid.presenter;


import com.werockstar.reactiveandroid.api.RxApi;

import javax.inject.Inject;

public class MainPresenter {


    private MainPresenter.View view;
    private RxApi api;

    @Inject
    public MainPresenter(RxApi api) {
        this.api = api;
    }

    public interface View {

    }

    public void attachView(MainPresenter.View view) {
        this.view = view;
    }
}
