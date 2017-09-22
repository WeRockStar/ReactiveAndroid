package com.werockstar.reactiveandroid.di


import com.werockstar.reactiveandroid.view.FormActivity
import com.werockstar.reactiveandroid.view.GithubActivity
import com.werockstar.reactiveandroid.view.MainActivity
import com.werockstar.reactiveandroid.view.SearchActivity

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(HttpModule::class, AndroidModule::class))
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: FormActivity)
    fun inject(activity: GithubActivity)
    fun inject(activity: SearchActivity)
}
