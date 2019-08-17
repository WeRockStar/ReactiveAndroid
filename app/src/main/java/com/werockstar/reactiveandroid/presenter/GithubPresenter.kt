package com.werockstar.reactiveandroid.presenter


import com.werockstar.reactiveandroid.api.RxApi
import com.werockstar.reactiveandroid.model.GithubUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class GithubPresenter @Inject constructor(private val api: RxApi) {

    private lateinit var view: GithubPresenter.View
    private val disposable by lazy { CompositeDisposable() }

    interface View {
        fun onUsersResult(users: List<GithubUserResponse>)

        fun onUsersError()
    }

    fun injectView(view: GithubPresenter.View) {
        this.view = view
    }

    fun getUsers() {
        val werockstarObs = api.getUser("werockstar").subscribeOn(Schedulers.io())
        val googleObs = api.getUser("google").subscribeOn(Schedulers.io())
        val facebookObs = api.getUser("facebook").subscribeOn(Schedulers.io())
        val airbnbObs = api.getUser("airbnb").subscribeOn(Schedulers.io())
        val microsoftObs = api.getUser("microsoft").subscribeOn(Schedulers.io())

        Observables.zip(werockstarObs, googleObs, facebookObs, airbnbObs, microsoftObs) { w, f, g, a, m ->
            listOf<GithubUserResponse>(w, f, g, a, m)
        }.onErrorReturn {
            listOf(GithubUserResponse("ไม่มีนะ", "นี่ก็ไม่มี", "และนี่ก็ไม่มี"))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry { attempt, throwable ->
                    val exception = throwable as HttpException
                    exception.code() != 403 && attempt <= 3
                }
                .subscribe({
                    view.onUsersResult(it)
                }, {
                    view.onUsersError()
                }).addTo(disposable)
    }

    fun dispose() {
        disposable.clear()
    }
}
