package com.werockstar.reactiveandroid.presenter


import com.werockstar.reactiveandroid.api.RxApi
import com.werockstar.reactiveandroid.model.GithubUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class GithubPresenter @Inject
constructor(private val api: RxApi) {

    private lateinit var view: GithubPresenter.View
    private val disposable = CompositeDisposable()
    private val TAG = GithubPresenter::class.java.simpleName

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

        disposable.add(Observables.zip(werockstarObs, googleObs, facebookObs, airbnbObs, microsoftObs, { w, f, g, a, m ->
            val users = arrayListOf<GithubUserResponse>()
            users.add(w)
            users.add(f)
            users.add(g)
            users.add(a)
            users.add(m)
            users
        })
                .onErrorReturn {
                    val users = ArrayList<GithubUserResponse>()
                    users.add(GithubUserResponse("ไม่มีนะ", "นี่ก็ไม่มี", "และนี่ก็ไม่มี"))
                    users
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry { attempt, throwable ->
                    val exception = throwable as HttpException
                    exception.code() != 403 && attempt <= 3
                }
                .subscribe({
                    view.onUsersResult(it)
                }, {
                    view.onUsersError()
                }
                )
        )


    }

    fun dispose() {
        disposable.clear()
    }
}