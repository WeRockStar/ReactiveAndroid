package com.werockstar.reactiveandroid.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import com.werockstar.reactiveandroid.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

	private val disposable = CompositeDisposable()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_form)


		val usernameObs = edtUsername.textChanges()
			.map { it.toString() }

		val passwordObs = edtPassword.textChanges()
			.map { it.toString() }

		val ageObs = edtAge.textChanges()
			.map { it.toString() }
			.map {
				when (it.isEmpty()) {
					true -> "0"
					else -> it
				}
			}
			.map { it.toInt() }

		Observables.combineLatest(usernameObs, passwordObs, ageObs)
			{ user, password, age -> user.length > 5 && password.length > 3 && age > 0 }
			.retry(2)
			.subscribe({
				btnSubmit.isEnabled = it
			}, {
				btnSubmit.isEnabled = false
				Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
			}).addTo(disposable)
	}

	override fun onDestroy() {
		disposable.clear()
		super.onDestroy()
	}
}
