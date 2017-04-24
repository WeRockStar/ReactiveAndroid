package com.werockstar.reactiveandroid.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity implements GithubPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
