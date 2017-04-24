package com.werockstar.reactiveandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.werockstar.reactiveandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvBlog) TextView tvBlog;
    @BindView(R.id.tvName) TextView tvName;

    public GithubViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }
}
