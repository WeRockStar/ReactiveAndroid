package com.werockstar.reactiveandroid.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.werockstar.reactiveandroid.R
import com.werockstar.reactiveandroid.model.GithubUserResponse
import javax.inject.Inject

class GithubAdapter @Inject constructor() : RecyclerView.Adapter<GithubViewHolder>() {

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
       holder.bind(user ?: return)
    }

    override fun getItemCount() = users?.size ?: 0

}
