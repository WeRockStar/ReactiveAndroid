package com.werockstar.reactiveandroid.adapter

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.werockstar.reactiveandroid.R
import com.werockstar.reactiveandroid.model.GithubUserResponse
import javax.inject.Inject


class GithubAdapter @Inject constructor(val application: Application) : RecyclerView.Adapter<GithubViewHolder>() {

    private var users: List<GithubUserResponse>? = null

    fun setUsers(users: List<GithubUserResponse>) {
        this.users = users
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.github_item_row, parent, false)
        return GithubViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = users?.get(position)

        holder.tvName.text = user?.name
        holder.tvBlog.text = user?.blog
        Glide.with(application).load(user?.avatarUrl).error(R.mipmap.ic_launcher_round).into(holder.ivAvatar)
    }

    override fun getItemCount() = users?.size ?: 0

}
