package com.werockstar.reactiveandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.werockstar.reactiveandroid.R

import butterknife.BindView
import butterknife.ButterKnife

class GithubViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.ivAvatar) lateinit var ivAvatar: ImageView
    @BindView(R.id.tvBlog) lateinit var tvBlog: TextView
    @BindView(R.id.tvName) lateinit var tvName: TextView

    init {

        ButterKnife.bind(this, view)
    }
}
