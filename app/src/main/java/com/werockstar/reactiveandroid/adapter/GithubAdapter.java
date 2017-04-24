package com.werockstar.reactiveandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.model.GithubUserResponse;

import java.util.List;


public class GithubAdapter extends RecyclerView.Adapter<GithubViewHolder> {

    List<GithubUserResponse> githubUsers;

    public void setGithubUsers(List<GithubUserResponse> githubUsers) {
        this.githubUsers = githubUsers;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_item_row, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return githubUsers == null ? 0 : githubUsers.size();
    }
}
