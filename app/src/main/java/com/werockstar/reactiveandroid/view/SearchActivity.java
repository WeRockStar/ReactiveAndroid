package com.werockstar.reactiveandroid.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.ReactiveApplication;
import com.werockstar.reactiveandroid.api.RxApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.edtSearch)
    EditText edtSearch;

    @Inject
    RxApi api;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final static String TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ((ReactiveApplication) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        disposable.add(RxTextView.textChanges(edtSearch)
                .debounce(700, TimeUnit.MICROSECONDS)
                .map(user -> user.toString())
                .filter(githubUser -> !githubUser.equalsIgnoreCase(""))
                .switchMap(user -> api.getUser(user).subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(githubInfo -> {
                    Toast.makeText(this, githubInfo.getName(), Toast.LENGTH_LONG).show();
                }, throwable -> {
                    Log.e(TAG, "Error: " + throwable.getMessage());
                })
        );
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
