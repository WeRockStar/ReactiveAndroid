package com.werockstar.reactiveandroid.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.ReactiveApplication;
import com.werockstar.reactiveandroid.api.RxApi;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class GithubActivity extends AppCompatActivity {

    @BindView(R.id.btnLoad)
    Button btnLoad;

    @Inject
    RxApi api;

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final String TAG = GithubActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        ((ReactiveApplication) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLoad)
    void onClickLoad() {
        String[] username = {"werockstar", "google", "facebook", "airbnb", "microsoft"};

        disposable.add(Observable.fromArray(username)
                .flatMap(user -> api.getUser(user).subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry((attempt, throwable) -> {
                    HttpException exception = (HttpException) throwable;
                    return exception.code() != 403 && attempt <= 3;
                })
                .subscribe(
                        info -> {
                        }, throwable -> {
                            Log.e(TAG, "Error", throwable);
                        }
                )
        );
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
