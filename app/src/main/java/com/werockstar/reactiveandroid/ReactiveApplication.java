package com.werockstar.reactiveandroid

import android.app.Application

import com.crashlytics.android.Crashlytics
import com.werockstar.reactiveandroid.di.AndroidModule
import com.werockstar.reactiveandroid.di.ApplicationComponent
import com.werockstar.reactiveandroid.di.DaggerApplicationComponent
import com.werockstar.reactiveandroid.di.HttpModule
import io.fabric.sdk.android.Fabric


class ReactiveApplication : Application() {

    var component: ApplicationComponent
        internal set

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .httpModule(HttpModule())
                .build()
    }
}
