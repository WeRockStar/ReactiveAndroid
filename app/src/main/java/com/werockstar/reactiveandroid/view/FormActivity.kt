package com.werockstar.reactiveandroid.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.werockstar.reactiveandroid.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val usernameObs = RxTextView.textChanges(edtUsername)
                .map { it.toString() }

        val passwordObs = RxTextView.textChanges(edtPassword)
                .map { it.toString() }

        val ageObs = RxTextView.textChanges(edtAge)
                .map { it.toString() }
                .map {
                    when (it.isEmpty()) {
                        true -> "0"
                        else -> it
                    }
                }
                .map { Integer.parseInt(it) }


        disposable.add(Observables.combineLatest<String, String, Int, Boolean>(usernameObs, passwordObs, ageObs)
        { user, password, age -> user.length > 5 && password.length > 3 && age > 0 }
                .retry(2)
                .subscribe({
                    btnSubmit.isEnabled = it
                }, {
                    btnSubmit.isEnabled = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
        )

    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}
