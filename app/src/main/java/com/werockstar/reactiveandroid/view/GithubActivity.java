package com.werockstar.reactiveandroid.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.ReactiveApplication;
import com.werockstar.reactiveandroid.adapter.GithubAdapter;
import com.werockstar.reactiveandroid.model.GithubUserResponse;
import com.werockstar.reactiveandroid.presenter.GithubPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GithubActivity extends AppCompatActivity implements GithubPresenter.View {

    @BindView(R.id.btnLoad)
    Button btnLoad;

    @BindView(R.id.usersList)
    RecyclerView rvUsersList;

    private final String TAG = GithubActivity.class.getSimpleName();

    @Inject
    GithubAdapter adapter;
    @Inject
    GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        ((ReactiveApplication) getApplication()).getComponent().inject(this);
        presenter.injectView(this);

        ButterKnife.bind(this);


        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        rvUsersList.setAdapter(adapter);
        rvUsersList.setHasFixedSize(true);
        rvUsersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnLoad)
    void onClickLoad() {
        presenter.getUsers();
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }

    @Override
    public void onUsersResult(List<GithubUserResponse> users) {
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUsersError() {

    }
}
