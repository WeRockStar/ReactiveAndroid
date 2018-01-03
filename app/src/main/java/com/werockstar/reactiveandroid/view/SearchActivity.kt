package com.werockstar.reactiveandroid.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import com.werockstar.reactiveandroid.R
import com.werockstar.reactiveandroid.ReactiveApplication
import com.werockstar.reactiveandroid.api.RxApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @BindView(R.id.edtSearch) lateinit var edtSearch: EditText

    @Inject lateinit var api: RxApi

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        (application as ReactiveApplication).component.inject(this)

        ButterKnife.bind(this)

        disposable.add(RxTextView.textChanges(edtSearch)
                .debounce(700, TimeUnit.MICROSECONDS)
                .map { it.toString() }
                .filter { it != "" }
                .switchMap { api.getUser(it).subscribeOn(Schedulers.io()) }
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
                }) { Log.e(TAG, "Error: " + it.message) }
        )
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    companion object {

        private val TAG = SearchActivity::class.java.simpleName
    }
}
