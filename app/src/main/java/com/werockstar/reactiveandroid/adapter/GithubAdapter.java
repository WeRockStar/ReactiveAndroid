package com.werockstar.reactiveandroid.adapter;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.werockstar.reactiveandroid.R;
import com.werockstar.reactiveandroid.model.GithubUserResponse;

import java.util.List;

import javax.inject.Inject;


public class GithubAdapter extends RecyclerView.Adapter<GithubViewHolder> {

    private List<GithubUserResponse> users;
    private Context context;

    @Inject
    public GithubAdapter(Application context) {
        this.context = context;
    }

    public void setUsers(List<GithubUserResponse> users) {
        this.users = users;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_item_row, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        GithubUserResponse user = users.get(position);

        holder.tvName.setText(user.getName());
        holder.tvBlog.setText(user.getBlog());
        Glide.with(context)
                .load(user.getAvatarUrl())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }
}
