package com.werockstar.reactiveandroid.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.werockstar.reactiveandroid.R
import com.werockstar.reactiveandroid.model.GithubUserResponse
import kotlinx.android.synthetic.main.github_item_row.view.*

class GithubViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

	fun bind(user: GithubUserResponse) {
		view.tvName.text = user.name
		view.tvBlog.text = user.blog
		Glide.with(view.context).load(user.avatarUrl).error(R.mipmap.ic_launcher_round)
			.into(view.ivAvatar)
	}
}
