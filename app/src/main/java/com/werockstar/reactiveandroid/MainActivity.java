package com.werockstar.reactiveandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.werockstar.reactiveandroid.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ReactiveApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);

    }
}
