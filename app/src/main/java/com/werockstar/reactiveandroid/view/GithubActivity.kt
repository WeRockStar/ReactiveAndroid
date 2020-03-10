package com.werockstar.reactiveandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.werockstar.reactiveandroid.R
import com.werockstar.reactiveandroid.ReactiveApplication
import com.werockstar.reactiveandroid.adapter.GithubAdapter
import com.werockstar.reactiveandroid.model.GithubUserResponse
import com.werockstar.reactiveandroid.presenter.GithubPresenter
import kotlinx.android.synthetic.main.activity_github.*
import javax.inject.Inject

class GithubActivity : AppCompatActivity(), GithubPresenter.View {


    private val TAG = GithubActivity::class.java.simpleName

    @Inject lateinit var githubAdapter: GithubAdapter
    @Inject lateinit var presenter: GithubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        (application as ReactiveApplication).component.inject(this)
        presenter.injectView(this)

        setUpRecyclerView()
        onClickLoadUser()
    }

    private fun onClickLoadUser() {
        btnLoad.setOnClickListener {
            presenter.getUsers()
        }
    }

    private fun setUpRecyclerView() {
        usersList.apply {
            adapter = githubAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun onUsersResult(users: List<GithubUserResponse>) {
        githubAdapter.apply {
            setUsers(users)
            notifyDataSetChanged()
        }
    }

    override fun onUsersError() {

    }
}
