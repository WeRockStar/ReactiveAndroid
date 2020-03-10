package com.werockstar.reactiveandroid.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import com.werockstar.reactiveandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

		btnForm.setOnClickListener(this)
		btnRequestApi.setOnClickListener(this)
		btnTyping.setOnClickListener(this)
    }

	override fun onClick(v: View?) {
		when (v?.id) {
			R.id.btnForm -> startActivity(Intent(this, FormActivity::class.java))
			R.id.btnTyping -> startActivity(Intent(this, SearchActivity::class.java))
			R.id.btnRequestApi -> startActivity(Intent(this, GithubActivity::class.java))
		}
	}

}
