package com.werockstar.reactiveandroid.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.ReactiveApplication;
import com.werockstar.reactiveandroid.presenter.GithubPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubActivity extends AppCompatActivity implements GithubPresenter.View {

    @Inject
    GithubPresenter presenter;

    @BindView(R.id.btnLoad)
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        ButterKnife.bind(this);

        ((ReactiveApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);

    }
}
