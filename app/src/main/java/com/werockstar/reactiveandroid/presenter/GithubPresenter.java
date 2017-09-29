package com.werockstar.reactiveandroid.presenter;


import com.werockstar.reactiveandroid.api.RxApi;
import com.werockstar.reactiveandroid.model.GithubUserResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class GithubPresenter {

    private RxApi api;
    private GithubPresenter.View view;

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final String TAG = GithubPresenter.class.getSimpleName();

    @Inject
    public GithubPresenter(RxApi api) {
        this.api = api;
    }

    public interface View {
        void onUsersResult(List<GithubUserResponse> users);

        void onUsersError();
    }

    public void injectView(GithubPresenter.View view) {
        this.view = view;
    }

    public void getUsers() {
        Observable<GithubUserResponse> werockstarObs = api.getUser("werockstar").subscribeOn(Schedulers.io());
        Observable<GithubUserResponse> googleObs = api.getUser("google").subscribeOn(Schedulers.io());
        Observable<GithubUserResponse> facebookObs = api.getUser("facebook").subscribeOn(Schedulers.io());
        Observable<GithubUserResponse> airbnbObs = api.getUser("airbnb").subscribeOn(Schedulers.io());
        Observable<GithubUserResponse> microsoftObs = api.getUser("microsoft").subscribeOn(Schedulers.io());

        disposable.add(Observable.zip(werockstarObs, googleObs, facebookObs, airbnbObs,
                microsoftObs, (w, f, g, a, m) -> {
                    final List<GithubUserResponse> users = new ArrayList<>();
                    users.add(w);
                    users.add(f);
                    users.add(g);
                    users.add(a);
                    users.add(m);
                    return users;
                })
                .onErrorReturn(__ -> {
                    List<GithubUserResponse> users = new ArrayList<>();
                    users.add(new GithubUserResponse("ไม่มีนะ", "นี่ก็ไม่มี", "และนี่ก็ไม่มี"));
                    return users;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry((attempt, throwable) -> {
                    HttpException exception = (HttpException) throwable;
                    return exception.code() != 403 && attempt <= 3;
                })
                .subscribe(
                        users -> {
                            view.onUsersResult(users);
                        }, throwable -> {
                            view.onUsersError();
                        }
                )
        );


    }

    public void dispose() {
        disposable.clear();
    }
}
