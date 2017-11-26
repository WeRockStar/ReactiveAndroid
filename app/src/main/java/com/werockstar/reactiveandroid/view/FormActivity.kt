package com.werockstar.reactiveandroid.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import com.werockstar.reactiveandroid.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables

class FormActivity : AppCompatActivity() {

    @BindView(R.id.edtUsername) lateinit var edtUsername: EditText
    @BindView(R.id.edtPassword) lateinit var edtPassword: EditText
    @BindView(R.id.edtAge) lateinit var edtAge: EditText
    @BindView(R.id.btnSubmit) lateinit var btnSubmit: Button

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        ButterKnife.bind(this)

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
                .subscribe(
                        { btnSubmit.isEnabled = it }
                ) {
                    btnSubmit.isEnabled = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })

    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}
