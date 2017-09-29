package com.werockstar.reactiveandroid.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.werockstar.reactiveandroid.R

import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
    }

    @OnClick(R.id.btnForm, R.id.btnRequestApi, R.id.btnTyping)
    fun onClickGoToExample(view: View) {
        when (view.id) {
            R.id.btnForm -> startActivity(Intent(this, FormActivity::class.java))
            R.id.btnTyping -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.btnRequestApi -> startActivity(Intent(this, GithubActivity::class.java))
        }
    }
}
